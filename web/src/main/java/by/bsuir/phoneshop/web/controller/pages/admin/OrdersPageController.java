package by.bsuir.phoneshop.web.controller.pages.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.bsuir.phoneshop.core.models.Order;
import by.bsuir.phoneshop.core.service.OrderService;
import by.bsuir.phoneshop.web.controller.constants.PhoneshopPages;

@Controller
@RequestMapping(value = "/admin/orders")
public class OrdersPageController
{

	@Resource
	private OrderService orderServiceImpl;

	@RequestMapping(method = RequestMethod.GET)
	public String getOrders(final Model model)
	{
		final List<Order> orders = orderServiceImpl.getOrders(Integer.MAX_VALUE, 0);

		if (orders != null)
		{
			model.addAttribute(orders);
		}

		return PhoneshopPages.UserPages.OrdersPage;
	}
}
