package by.bsuir.phoneshop.web.controller.pages;

import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import by.bsuir.phoneshop.core.models.Cart;
import by.bsuir.phoneshop.core.dto.OrderDataDto;
import by.bsuir.phoneshop.core.service.CartService;
import by.bsuir.phoneshop.core.service.OrderService;
import by.bsuir.phoneshop.core.validator.OrderDataDtoValidator;
import by.bsuir.phoneshop.web.controller.constants.PhoneshopPages;

@Controller
@PropertySource("classpath:/messages_en.properties")
@RequestMapping(value = "/order")
public class OrderPageController
{
	@Resource
	private OrderService orderService;
	@Resource
	private CartService cartService;
	@Resource
	private HttpSession httpSession;
	@Resource
	private OrderDataDtoValidator orderDataDtoValidator;
	@Resource
	private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public String getOrder(final Model model)
	{
		model.addAttribute("errors", model.asMap().get("errors"));
		model.addAttribute("error", model.asMap().get("error"));

		Cart cart = cartService.getCart(httpSession);
		showCartAsOrder(cart, model);

		return PhoneshopPages.UserPages.OrderPage;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String placeOrder(final @Validated @ModelAttribute(name = "orderDataDto") OrderDataDto orderDataDto,
									 final BindingResult bindingResult,
									 final RedirectAttributes redirectAttributes) throws RuntimeException
	{
		final Cart cart = cartService.getCart(httpSession);
		if (cart.getCartItems().isEmpty())
		{
			return prepareModelForEmptyCart(redirectAttributes);
		}

		orderDataDtoValidator.validate(orderDataDto, bindingResult);

		if (bindingResult.hasErrors())
		{
			return prepareModelForValidationErrors(bindingResult, redirectAttributes);
		}

		final Long id = orderService.placeOrder(cart, orderDataDto, Long.parseLong(messageSource.getMessage("delivery.price", null, LocaleContextHolder.getLocale())));
		cartService.deleteCart(httpSession);

		return "redirect:/" + PhoneshopPages.UserPages.OrderOverviewPage + "/" + id;
	}

	private String prepareModelForValidationErrors(final BindingResult bindingResult,
																  final RedirectAttributes redirectAttributes)
	{
		redirectAttributes.addFlashAttribute("error", true);
		redirectAttributes.addFlashAttribute("errors", bindingResult);
		return "redirect:/" + PhoneshopPages.UserPages.OrderPage;
	}

	private String prepareModelForEmptyCart(final RedirectAttributes redirectAttributes)
	{
		redirectAttributes.addFlashAttribute("error", true);
		return "redirect:/" + PhoneshopPages.UserPages.OrderPage;
	}

	private void showCartAsOrder(final Cart cart, final Model model)
	{
		model.addAttribute("cart", cart);
		final BigDecimal deliveryPrice = BigDecimal.valueOf(Long.parseLong(
					 messageSource.getMessage("delivery.price", null, LocaleContextHolder.getLocale())));

		model.addAttribute("deliveryPrice", deliveryPrice);
		model.addAttribute("totalCost", cart.getTotalCost().add(deliveryPrice));
	}

	@ExceptionHandler(RuntimeException.class)
	public String handleError()
	{
		return "redirect:/error?message=" + messageSource.getMessage("exception.outOfStock", null, LocaleContextHolder.getLocale());
	}
}
