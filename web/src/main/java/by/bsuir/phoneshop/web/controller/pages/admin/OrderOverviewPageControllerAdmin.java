package by.bsuir.phoneshop.web.controller.pages.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import by.bsuir.phoneshop.core.enums.OrderStatus;
import by.bsuir.phoneshop.core.service.OrderService;
import by.bsuir.phoneshop.web.controller.constants.PhoneshopPages;

@Controller
@RequestMapping(value = "/admin/orders/{id}")
public class OrderOverviewPageControllerAdmin
{

	@Resource
	private OrderService orderServiceImpl;

	@RequestMapping(method = RequestMethod.GET)
	public String getOrderOverview(final @PathVariable("id") Long id, final Model model)
	{
		orderServiceImpl.getOrderById(id).ifPresent(order -> model.addAttribute("order", order));
		model.addAttribute("id", id);
		return PhoneshopPages.AdminPages.OrderOverviewPageAdmin;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String changeOrderStatus(final @PathVariable("id") Long orderId,
											  final @RequestParam(name = "orderStatus") String orderStatus)
	{
		if (OrderStatus.DELIVERED.name().equals(orderStatus))
		{
			orderServiceImpl.updateStatus(OrderStatus.DELIVERED, orderId);
		}
		else
		{
			orderServiceImpl.updateStatus(OrderStatus.REJECTED, orderId);
		}
		return "redirect:" + PhoneshopPages.AdminPages.OrdersPage + orderId;
	}

	@ExceptionHandler(RuntimeException.class)
	public String handleOutOfStock()
	{
		return "redirect:/404?message=" + "no such order";
	}
}
