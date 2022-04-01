package by.bsuir.phoneshop.core.dao;

import java.util.List;
import java.util.Optional;

import by.bsuir.phoneshop.core.beans.Order;
import by.bsuir.phoneshop.core.enums.OrderStatus;

public interface OrderDao
{

	Optional<Order> get(final Long key);

	Long save(final Order order);

	List<Order> getOrders(int limit, int offset);

	void updateStatus(OrderStatus orderStatus, Long key);
}
