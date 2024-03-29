package by.bsuir.phoneshop.web.controller.pages;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.bsuir.phoneshop.core.models.Phone;
import by.bsuir.phoneshop.core.service.CartService;
import by.bsuir.phoneshop.core.service.PhoneService;
import by.bsuir.phoneshop.web.controller.constants.PhoneshopPages;

@Controller
@RequestMapping(value = "/product-details/{id}")
public class ProductDetailsPageController
{
	@Resource
	private PhoneService phoneServiceImpl;

	@Resource
	private CartService httpSessionCartService;

	@Resource
	private HttpSession httpSession;

	@RequestMapping(method = RequestMethod.GET)
	public String showProductDetailsInfo(final @PathVariable("id") Long phoneId, Model model)
	{
		final Phone phone = phoneServiceImpl.getPhoneByKey(phoneId).orElse(null);
		model.addAttribute("phone", phone);
		model.addAttribute("cart", httpSessionCartService.getCart(httpSession));

		return PhoneshopPages.UserPages.ProductDetailsPage;
	}
}
