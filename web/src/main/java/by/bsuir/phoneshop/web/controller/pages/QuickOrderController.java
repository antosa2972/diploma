package by.bsuir.phoneshop.web.controller.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import by.bsuir.phoneshop.core.beans.Cart;
import by.bsuir.phoneshop.core.service.CartService;
import by.bsuir.phoneshop.core.exception.OutOfStockException;
import by.bsuir.phoneshop.core.beans.Phone;
import by.bsuir.phoneshop.core.dao.PhoneDao;
import by.bsuir.phoneshop.core.beans.QuickOrderElement;
import by.bsuir.phoneshop.core.dto.QuickOrderElementsDto;

@Controller
@RequestMapping(value = "/quickOrder")
public class QuickOrderController
{

	@Resource
	private HttpSession httpSession;

	@Resource
	private PhoneDao jdbcPhoneDao;

	@Resource
	private CartService httpSessionCartService;

	private List<Phone> phoneList;

	private QuickOrderController()
	{
		this.phoneList = new ArrayList<>();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getQuickOrderPage(final Model model)
	{
		model.addAttribute("success", model.asMap().get("success"));
		model.addAttribute("quickOrderElementsDto", new QuickOrderElementsDto());
		return "quickOrder";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String makeQuickOrder(final @ModelAttribute(name = "quickOrderElementsDto") @Validated QuickOrderElementsDto quickOrderElementsDto,
										  final BindingResult bindingResult,
										  final RedirectAttributes redirectAttributes,
										  final Model model)
	{
		Cart cart = httpSessionCartService.getCart(httpSession);
		quickOrderElementsDto.getQuickOrderElements().forEach(quickOrderElement ->
		{
			int indexInList = quickOrderElementsDto.getQuickOrderElements().indexOf(quickOrderElement);
			addToCart(quickOrderElementsDto, cart, bindingResult, indexInList);
		});

		if (bindingResult.hasFieldErrors())
		{
			model.addAttribute("successfulPhones", phoneList);
			model.addAttribute("errors", bindingResult);
			return "quickOrder";
		}

		redirectAttributes.addFlashAttribute("success", true);

		return "redirect:quickOrder";
	}

	private void addToCart(final QuickOrderElementsDto quickOrderElementsDto,
								  final Cart cart, final BindingResult bindingResult, final int indexInList)
	{
		final QuickOrderElement quickOrderElement = quickOrderElementsDto.getQuickOrderElements().get(indexInList);
		final Long quantity = quickOrderElement.getQuantity();
		final String modelPhone = quickOrderElement.getModel();
		if (quantity == null && modelPhone.isEmpty())
		{
			return;
		}
		final Optional<Phone> optionalPhone = jdbcPhoneDao.get(modelPhone);
		optionalPhone.ifPresent(phone ->
		{
			if (quantity != null)
			{
				try
				{
					httpSessionCartService.addPhone(phone.getId(), quantity, cart);
					quickOrderElementsDto.getQuickOrderElements().set(indexInList, new QuickOrderElement());
				}
				catch (OutOfStockException e)
				{
					bindingResult.rejectValue("quickOrderElements[" + indexInList + "].quantity",
								 "errors.quantity.outOfStock", "Out of stock!");
				}
				phoneList.add(phone);
			}
			else
			{
				bindingResult.rejectValue("quickOrderElements[" + indexInList + "].quantity",
							 "errors.emptyField", "Empty field!");
			}
		});
		if (!optionalPhone.isPresent())
		{
			bindingResult.rejectValue("quickOrderElements[" + indexInList + "].model",
						 "errors.product.notFound ", "No such product!");
		}
	}
}
