package by.bsuir.phoneshop.core.service;

import java.util.List;
import java.util.Optional;

import by.bsuir.phoneshop.core.models.Cart;
import by.bsuir.phoneshop.core.models.Order;
import by.bsuir.phoneshop.core.dto.OrderDataDto;
import by.bsuir.phoneshop.core.models.enums.OrderStatus;

public interface OrderService
{
	Long placeOrder(final Cart cart, final OrderDataDto orderDataDto, final Long deliveryPrice) throws RuntimeException;

	List<Order> getOrders(final int limit, final int offset);

	void updateStatus(final OrderStatus orderStatus, final Long orderId);

	Optional<Order> getOrderById(final Long id);
}
