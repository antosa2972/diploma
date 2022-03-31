package com.es.core.order;

import java.util.List;

import com.es.core.cart.Cart;
import com.es.core.exception.OutOfStockException;
import com.es.core.model.order.Order;
import com.es.core.model.order.OrderDataDto;
import com.es.core.model.order.OrderStatus;

public interface OrderService
{
	Long placeOrder(Cart cart, OrderDataDto orderDataDto, Long deliveryPrice) throws OutOfStockException;

	List<Order> getOrders(int limit, int offset);

	void updateStatus(OrderStatus orderStatus, Long orderId);
}
