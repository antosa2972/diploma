package by.bsuir.phoneshop.web.controller;

import static org.springframework.http.ResponseEntity.badRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.bsuir.phoneshop.core.beans.Cart;
import by.bsuir.phoneshop.core.service.CartService;
import by.bsuir.phoneshop.core.dto.PhoneDto;
import by.bsuir.phoneshop.core.exception.OutOfStockException;
import by.bsuir.phoneshop.core.validator.ResponseErrors;
import by.bsuir.phoneshop.core.validator.ValidationErrors;

@Controller
@RequestMapping(value = "/ajaxCart")
public class AjaxCartController
{

	@Resource(name = "phoneDtoValidator")
	private Validator quantityValidator;

	@Resource
	private CartService cartService;

	@Resource
	private HttpSession httpSession;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> addPhone(@Validated @RequestBody PhoneDto phoneDto, BindingResult bindingResult)
	{
		quantityValidator.validate(phoneDto, bindingResult);
		if (bindingResult.hasErrors())
		{
			ValidationErrors validationErrors = new ValidationErrors(bindingResult.getAllErrors());
			return badRequest().body(validationErrors.getErrors().get(0).getCode());
		}
		try
		{
			Cart currentCart = cartService.getCart(httpSession);
			cartService.addPhone(phoneDto.getId(), phoneDto.getQuantity(), currentCart);
			return ResponseEntity.ok(cartService.getCart(httpSession));
		}
		catch (OutOfStockException | IllegalArgumentException e)
		{
			ResponseErrors errors = new ResponseErrors(e.getMessage());
			return ResponseEntity.badRequest().body(errors);
		}
		catch (Exception e)
		{
			return ResponseEntity.status(500).build();
		}
	}
}
