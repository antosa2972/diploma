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

import by.bsuir.phoneshop.core.models.Cart;
import by.bsuir.phoneshop.core.models.Phone;
import by.bsuir.phoneshop.core.dto.QuickOrderElement;
import by.bsuir.phoneshop.core.dto.QuickOrderElementsDto;
import by.bsuir.phoneshop.core.service.CartService;
import by.bsuir.phoneshop.core.service.PhoneService;
import by.bsuir.phoneshop.web.controller.constants.PhoneshopPages;

@Controller
@RequestMapping(value = "/quick-order")
public class QuickOrderController
{

	@Resource
	private HttpSession httpSession;

	@Resource
	private PhoneService phoneServiceImpl;

	@Resource
	private CartService httpSessionCartService;

	private final List<Phone> phoneList;

	private QuickOrderController()
	{
		this.phoneList = new ArrayList<>();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getQuickOrderPage(final Model model)
	{
		model.addAttribute("success", model.asMap().get("success"));
		model.addAttribute("quickOrderElementsDto", new QuickOrderElementsDto());
		return "quick-order";
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
			return PhoneshopPages.UserPages.QuickOrderPage;
		}

		redirectAttributes.addFlashAttribute("success", true);

		return "redirect:" + PhoneshopPages.UserPages.QuickOrderPage;
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
		final Optional<Phone> optionalPhone = phoneServiceImpl.getPhoneByModel(modelPhone);
		optionalPhone.ifPresent(phone ->
		{
			if (quantity != null)
			{
				try
				{
					httpSessionCartService.addPhone(phone.getId(), quantity, cart);
					quickOrderElementsDto.getQuickOrderElements().set(indexInList, new QuickOrderElement());
				}
				catch (RuntimeException e)
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
