package com.es.phoneshop.web.controller.pages.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.es.core.model.order.Order;
import com.es.core.order.OrderService;

@Controller
@RequestMapping(value = "/admin/orders")
public class OrdersPageController
{

	@Resource
	private OrderService orderServiceImpl;

	@RequestMapping(method = RequestMethod.GET)
	public String getCart(Model model)
	{
		List<Order> orders = orderServiceImpl.getOrders(Integer.MAX_VALUE, 0);
		if (orders != null)
		{
			model.addAttribute(orders);
		}
		return "orders";
	}
}
