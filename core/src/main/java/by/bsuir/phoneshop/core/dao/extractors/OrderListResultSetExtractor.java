package by.bsuir.phoneshop.core.dao.extractors;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import by.bsuir.phoneshop.core.models.Order;
import by.bsuir.phoneshop.core.models.OrderItem;
import by.bsuir.phoneshop.core.dao.PhoneDao;
import by.bsuir.phoneshop.core.models.enums.OrderStatus;

@Component
public class OrderListResultSetExtractor implements ResultSetExtractor<List<Order>>
{
	@Resource
	private PhoneDao jdbcPhoneDao;

	@Override
	public List<Order> extractData(final ResultSet resultSet) throws SQLException, DataAccessException
	{
		final Map<Order, List<OrderItem>> data = new LinkedHashMap<>();
		final List<Order> orders = new ArrayList<>();
		while (resultSet.next())
		{
			final Order order = configureOrderFields(resultSet);

			final Long orderItemId = resultSet.getLong("orderItems.id");
			final Long phoneId = resultSet.getLong("orderItems.phoneId");
			final Long quantity = resultSet.getLong("orderItems.quantity");

			data.putIfAbsent(order, new ArrayList<>());
			OrderItem orderItem = new OrderItem(orderItemId, jdbcPhoneDao.get(phoneId).orElse(null), order, quantity);
			data.get(order).add(orderItem);
		}
		for (Map.Entry<Order, List<OrderItem>> entry : data.entrySet())
		{
			final Order orderToAdd = entry.getKey();
			orderToAdd.setOrderItems(entry.getValue());
			orders.add(orderToAdd);
		}

		return orders;
	}

	private Order configureOrderFields(final ResultSet resultSet) throws SQLException
	{
		final Order order = new Order();

		order.setId(resultSet.getLong("orders.id"));
		order.setSubtotal(BigDecimal.valueOf(resultSet.getFloat("orders.subtotal")));
		order.setDeliveryPrice(BigDecimal.valueOf(resultSet.getFloat("orders.deliveryPrice")));
		order.setTotalPrice(BigDecimal.valueOf(resultSet.getFloat("orders.totalPrice")));
		order.setFirstName(resultSet.getString("orders.firstName"));
		order.setLastName(resultSet.getString("orders.lastName"));
		order.setDeliveryAddress(resultSet.getString("orders.deliveryAddress"));
		order.setContactPhoneNo(resultSet.getString("orders.contactPhoneNo"));
		order.setAdditionalInfo(resultSet.getString("orders.additionalInfo"));
		order.setStatus(OrderStatus.valueOf(resultSet.getString("orders.status")));
		order.setDate(resultSet.getObject("orders.date", Timestamp.class));

		return order;
	}
}
