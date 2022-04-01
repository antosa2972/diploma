package by.bsuir.phoneshop.core.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.bsuir.phoneshop.core.beans.Cart;
import by.bsuir.phoneshop.core.exception.OutOfStockException;
import by.bsuir.phoneshop.core.beans.Order;
import by.bsuir.phoneshop.core.dao.OrderDao;
import by.bsuir.phoneshop.core.dto.OrderDataDto;
import by.bsuir.phoneshop.core.beans.OrderItem;
import by.bsuir.phoneshop.core.enums.OrderStatus;
import by.bsuir.phoneshop.core.beans.Stock;
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
	public Long placeOrder(Cart cart, OrderDataDto orderDataDto, Long deliveryPrice) throws OutOfStockException
	{
		AtomicBoolean check = new AtomicBoolean(false);
		Order order = getOrder(cart, orderDataDto, deliveryPrice);
		order.getOrderItems().forEach(orderItem ->
		{
			Stock stock = jdbcStockDao.get(orderItem.getPhone().getId()).orElse(null);
			if (stock != null && stock.getStock() - orderItem.getQuantity() > 0)
			{
				jdbcStockDao.update(orderItem.getPhone().getId(), stock.getStock() - orderItem.getQuantity(),
							 stock.getReserved() - orderItem.getQuantity());
			}
			else
			{
				check.set(true);
			}
		});
		if (check.get())
		{
			throw new OutOfStockException();
		}
		order.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
		return jdbcOrderDao.save(order);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Order> getOrders(int limit, int offset)
	{
		return jdbcOrderDao.getOrders(limit, offset);
	}

	@Override
	public void updateStatus(OrderStatus orderStatus, Long orderId)
	{
		jdbcOrderDao.updateStatus(orderStatus, orderId);
	}

	private Order getOrder(Cart cart, OrderDataDto orderDataDto, Long deliveryPrice)
	{
		Order order = new Order();
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
			OrderItem orderItem = new OrderItem(cartItem, order);
			order.getOrderItems().add(orderItem);
		});
		return order;
	}
}