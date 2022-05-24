package by.bsuir.phoneshop.core.dao;

import java.util.List;
import java.util.Optional;

import by.bsuir.phoneshop.core.models.Order;
import by.bsuir.phoneshop.core.models.enums.OrderStatus;

public interface OrderDao
{

	Optional<Order> get(final Long key);

	Long save(final Order order);

	List<Order> getOrders(final int limit, final int offset);

	void updateStatus(final OrderStatus orderStatus, final Long key);
}
