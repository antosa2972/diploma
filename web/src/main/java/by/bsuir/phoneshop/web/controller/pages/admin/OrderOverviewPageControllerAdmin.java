package by.bsuir.phoneshop.web.controller.pages.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import by.bsuir.phoneshop.core.models.enums.OrderStatus;
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
		return PhoneshopPages.UserPages.OrderOverviewPageAdmin;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String changeOrderStatus(final @PathVariable("id") Long orderId,
											  final @RequestParam(name = "orderStatus") String orderStatus)
	{
		OrderStatus status = OrderStatus.REJECTED;
		if (OrderStatus.DELIVERED.name().equals(orderStatus))
		{
			status = OrderStatus.DELIVERED;
		}
		else if (OrderStatus.IN_DELIVERY.name().equals(orderStatus))
		{
			status = OrderStatus.IN_DELIVERY;
		}
		else
		{
			status = OrderStatus.REJECTED;
		}
		orderServiceImpl.updateStatus(status, orderId);

		return "redirect:/admin/orders/" + orderId;
	}

	@ExceptionHandler(RuntimeException.class)
	public String handleOutOfStock()
	{
		return "redirect:/error?message=" + "no such order";
	}
}
