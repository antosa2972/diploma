package by.bsuir.phoneshop.web.controller.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import by.bsuir.phoneshop.core.beans.Cart;
import by.bsuir.phoneshop.core.service.CartService;
import by.bsuir.phoneshop.core.dto.PhoneArrayDto;
import by.bsuir.phoneshop.core.beans.Phone;
import by.bsuir.phoneshop.core.dao.PhoneDao;
import by.bsuir.phoneshop.core.validator.PhoneArrayDtoValidator;

@Controller
@RequestMapping(value = "/cart")
public class CartPageController
{
	@Resource
	private CartService cartService;
	@Resource
	private HttpSession httpSession;
	@Resource
	private PhoneDao jdbcPhoneDao;
	@Resource
	private PhoneArrayDtoValidator phoneArrayDtoValidator;

	@RequestMapping(method = RequestMethod.GET)
	public String getCart(final @RequestParam(value = "successDelete", required = false) boolean successDelete,
								 final @RequestParam(value = "successUpdate", required = false) boolean successUpdate,
								 final @RequestParam(value = "isOutOfStock", required = false) boolean isOutOfStock,
								 final @RequestParam(value = "error", required = false) boolean error,
								 final @RequestParam(value = "errorsId", required = false) List<Long> errorsId,
								 final Model model)
	{
		final Cart cart = cartService.getCart(httpSession);

		model.addAttribute("successDelete", successDelete);
		model.addAttribute("successUpdate", successUpdate);
		model.addAttribute("isOutOfStock", isOutOfStock);
		model.addAttribute("error", error);
		model.addAttribute("errorsId", errorsId);
		model.addAttribute("cart", cart);

		return "cart";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String updateCart(final @Validated @ModelAttribute(name = "phoneArrayDto") PhoneArrayDto phoneArrayDto, final Model model, final BindingResult bindingResult)
	{
		final Cart cart = cartService.getCart(httpSession);

		if (cart.getCartItems().isEmpty())
		{
			return prepareModelForEmptyCart(cart, model);
		}

		phoneArrayDtoValidator.validate(phoneArrayDto, bindingResult);

		if (bindingResult.hasErrors())
		{
			return validationFailed(cart, bindingResult, model);
		}

		final HashMap<Long, Long> idAndQuantity = new HashMap<>();
		for (int i = 0; i < phoneArrayDto.getQuantity().length; i++)
		{
			idAndQuantity.put(Long.parseLong(phoneArrayDto.getPhoneId()[i]),
						 Long.parseLong(phoneArrayDto.getQuantity()[i]));
		}

		cartService.update(idAndQuantity, cart);
		model.addAttribute("successUpdate", true);
		model.addAttribute("cart", cart);
		return "redirect:/cart";
	}

	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public String deleteFromCart(final @PathVariable("id") Long id, final Model model) throws IllegalArgumentException
	{
		final Optional<Phone> optionalPhone = jdbcPhoneDao.get(id);
		final Cart cart = cartService.getCart(httpSession);

		if (cart.getCartItems().isEmpty())
		{
			return prepareModelForEmptyCart(cart, model);
		}

		if (optionalPhone.isPresent())
		{
			cartService.remove(id, cart);
			model.addAttribute("successDelete", true);
			model.addAttribute("cart", cart);
		}
		else
		{
			model.addAttribute("error", true);
			throw new IllegalArgumentException(String.valueOf(id));
		}

		return "redirect:/cart";
	}

	private String prepareModelForEmptyCart(final Cart cart, final Model model)
	{
		model.addAttribute("error", true);
		model.addAttribute("cart", cart);
		return "redirect:/cart";
	}

	private String validationFailed(final Cart cart, final BindingResult bindingResult, final Model model)
	{
		final List<FieldError> errorList = bindingResult.getFieldErrors("quantity");
		final List<Long> errorsId = errorList.stream()
					 .map(item -> Long.parseLong(item.getCode()))
					 .collect(Collectors.toList());

		model.addAttribute("cart", cart);
		model.addAttribute("errorsId", errorsId);
		model.addAttribute("error", true);

		return "redirect:/cart";
	}
}
