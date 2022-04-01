package by.bsuir.phoneshop.web.controller.pages.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import by.bsuir.phoneshop.core.dao.OrderDao;
import by.bsuir.phoneshop.core.enums.OrderStatus;
import by.bsuir.phoneshop.core.service.OrderService;

@Controller
@RequestMapping(value = "/admin/orders/{id}")
public class OrderOverviewPageControllerAdmin
{

	@Resource
	private OrderDao jdbcOrderDao;
	@Resource
	private OrderService orderServiceImpl;

	@RequestMapping(method = RequestMethod.GET)
	public String getOrderOverview(@PathVariable("id") Long id, Model model)
	{
		jdbcOrderDao.get(id).ifPresent(order -> model.addAttribute("order", order));
		model.addAttribute("id", id);
		return "orderOverviewAdmin";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String changeOrderStatus(@PathVariable("id") Long orderId,
											  @RequestParam(name = "orderStatus") String orderStatus)
	{
		if (OrderStatus.DELIVERED.name().equals(orderStatus))
		{
			orderServiceImpl.updateStatus(OrderStatus.DELIVERED, orderId);
		}
		else
		{
			orderServiceImpl.updateStatus(OrderStatus.REJECTED, orderId);
		}
		return "redirect:/admin/orders/" + orderId;
	}

	@ExceptionHandler(RuntimeException.class)
	public String handleOutOfStock()
	{
		return "redirect:/404?message=" + "no such order";
	}
}
