package by.bsuir.phoneshop.core.service;

import java.util.List;

import by.bsuir.phoneshop.core.beans.Cart;
import by.bsuir.phoneshop.core.exception.OutOfStockException;
import by.bsuir.phoneshop.core.beans.Order;
import by.bsuir.phoneshop.core.dto.OrderDataDto;
import by.bsuir.phoneshop.core.enums.OrderStatus;

public interface OrderService
{
	Long placeOrder(Cart cart, OrderDataDto orderDataDto, Long deliveryPrice) throws OutOfStockException;

	List<Order> getOrders(int limit, int offset);

	void updateStatus(OrderStatus orderStatus, Long orderId);
}
