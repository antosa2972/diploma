package by.bsuir.phoneshop.core.dao.extractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import by.bsuir.phoneshop.core.beans.Order;
import by.bsuir.phoneshop.core.beans.OrderItem;
import by.bsuir.phoneshop.core.dao.PhoneDao;

@Component
public class OrderResultSetExtractor extends AbstractOrderResultSetExtractor implements ResultSetExtractor<Order>
{
	@Resource
	private PhoneDao jdbcPhoneDao;

	@Override
	public Order extractData(final ResultSet resultSet) throws SQLException, DataAccessException
	{
		resultSet.next();
		List<OrderItem> orderItems = new ArrayList<>();
		final Order order = configureOrder(resultSet);
		do
		{
			final Long orderItemId = resultSet.getLong("orderItems.id");
			final Long phoneId = resultSet.getLong("orderItems.phoneId");
			final Long quantity = resultSet.getLong("orderItems.quantity");
			orderItems.add(new OrderItem(orderItemId, jdbcPhoneDao.get(phoneId).orElse(null), order, quantity));
		} while ((resultSet.next()));
		order.setOrderItems(orderItems);

		return order;
	}
}
