package by.bsuir.phoneshop.core.service;

import java.util.List;

import by.bsuir.phoneshop.core.beans.Cart;
import by.bsuir.phoneshop.core.exception.OutOfStockException;
import by.bsuir.phoneshop.core.beans.Order;
import by.bsuir.phoneshop.core.dto.OrderDataDto;
import by.bsuir.phoneshop.core.enums.OrderStatus;

public interface OrderService
{
	Long placeOrder(final Cart cart, final OrderDataDto orderDataDto, final Long deliveryPrice) throws OutOfStockException;

	List<Order> getOrders(final int limit, final int offset);

	void updateStatus(final OrderStatus orderStatus, final Long orderId);
}
