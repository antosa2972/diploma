package by.bsuir.phoneshop.web.controller.pages;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.bsuir.phoneshop.core.service.OrderService;
import by.bsuir.phoneshop.web.controller.constants.PhoneshopPages;

@Controller
@RequestMapping(value = "/order-overview/{id}")
public class OrderOverviewPageController
{
	@Resource
	OrderService orderServiceImpl;

	@RequestMapping(method = RequestMethod.GET)
	public String getOrderOverview(final @PathVariable("id") Long id, final Model model)
	{
		orderServiceImpl.getOrderById(id).ifPresent(order -> model.addAttribute("order", order));
		model.addAttribute("id", id);

		return PhoneshopPages.UserPages.OrderOverviewPage;
	}

	@ExceptionHandler(RuntimeException.class)
	public String handleOutOfStock()
	{
		return "redirect:/404?message=" + "no such order";
	}
}
