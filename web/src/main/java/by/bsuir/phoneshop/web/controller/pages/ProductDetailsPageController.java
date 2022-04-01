package by.bsuir.phoneshop.web.controller.pages;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.bsuir.phoneshop.core.service.CartService;
import by.bsuir.phoneshop.core.beans.Phone;
import by.bsuir.phoneshop.core.dao.PhoneDao;

@Controller
@RequestMapping(value = "/productDetails/{id}")
public class ProductDetailsPageController
{
	@Resource
	private PhoneDao jdbcPhoneDao;
	@Resource
	private CartService httpSessionCartService;
	@Resource
	private HttpSession httpSession;

	@RequestMapping(method = RequestMethod.GET)
	public String showProductDetailsInfo(@PathVariable("id") Long phoneId, Model model)
	{
		Phone phone = jdbcPhoneDao.get(phoneId).orElse(null);
		model.addAttribute("phone", phone);
		model.addAttribute("cart", httpSessionCartService.getCart(httpSession));
		return "productDetails";
	}
}