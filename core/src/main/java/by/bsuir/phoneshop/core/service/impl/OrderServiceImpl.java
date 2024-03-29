package by.bsuir.phoneshop.core.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.bsuir.phoneshop.core.models.Cart;
import by.bsuir.phoneshop.core.models.Order;
import by.bsuir.phoneshop.core.dao.OrderDao;
import by.bsuir.phoneshop.core.dto.OrderDataDto;
import by.bsuir.phoneshop.core.models.OrderItem;
import by.bsuir.phoneshop.core.models.enums.OrderStatus;
import by.bsuir.phoneshop.core.models.Stock;
import by.bsuir.phoneshop.core.dao.StockDao;
import by.bsuir.phoneshop.core.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService
{

	@Resource
	private OrderDao jdbcOrderDao;
	@Resource
	private StockDao jdbcStockDao;

	@Override
	@Transactional(rollbackFor = DataAccessException.class)
	public Long placeOrder(final Cart cart, OrderDataDto orderDataDto, final Long deliveryPrice) throws RuntimeException
	{
		final AtomicBoolean isOutOfStock = new AtomicBoolean(false);
		final Order order = createOrderFromCart(cart, orderDataDto, deliveryPrice);
		order.getOrderItems().forEach(orderItem ->
		{
			final Stock stock = jdbcStockDao.get(orderItem.getPhone().getId()).orElse(null);
			if (stock != null && stock.getStock() - orderItem.getQuantity() > 0)
			{
				jdbcStockDao.update(orderItem.getPhone().getId(), stock.getStock() - orderItem.getQuantity(),
							 stock.getReserved() - orderItem.getQuantity(), false);
			}
			else
			{
				isOutOfStock.set(true);
			}
		});

		if (isOutOfStock.get())
		{
			throw new RuntimeException();
		}

		order.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);

		return jdbcOrderDao.save(order);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Order> getOrders(final int limit,final int offset)
	{
		return jdbcOrderDao.getOrders(limit, offset);
	}

	@Override
	public void updateStatus(final OrderStatus orderStatus, final Long orderId)
	{
		jdbcOrderDao.updateStatus(orderStatus, orderId);
	}

	@Override
	public Optional<Order> getOrderById(final Long id)
	{
		return jdbcOrderDao.get(id);
	}

	private Order createOrderFromCart(final Cart cart, final OrderDataDto orderDataDto, final Long deliveryPrice)
	{
		final Order order = new Order();

		order.setSubtotal(cart.getTotalCost());
		order.setDeliveryPrice(BigDecimal.valueOf(deliveryPrice));
		order.setTotalPrice(order.getSubtotal().add(order.getDeliveryPrice()));
		order.setStatus(OrderStatus.NEW);
		order.setFirstName(orderDataDto.getFirstName());
		order.setLastName(orderDataDto.getLastName());
		order.setContactPhoneNo(orderDataDto.getPhone());
		order.setDeliveryAddress(orderDataDto.getAddress());
		order.setAdditionalInfo(orderDataDto.getAdditionalInfo());

		cart.getCartItems().forEach(cartItem ->
		{
			final OrderItem orderItem = new OrderItem(cartItem, order);
			order.getOrderItems().add(orderItem);
		});

		return order;
	}
}
