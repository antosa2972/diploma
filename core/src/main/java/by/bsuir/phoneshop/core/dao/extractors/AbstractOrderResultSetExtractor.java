package by.bsuir.phoneshop.core.dao.extractors;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import by.bsuir.phoneshop.core.beans.Order;
import by.bsuir.phoneshop.core.enums.OrderStatus;

public abstract class AbstractOrderResultSetExtractor
{
	public Order configureOrder(final ResultSet resultSet) throws SQLException
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
