package by.bsuir.phoneshop.web.controller.pages.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import by.bsuir.phoneshop.web.controller.constants.PhoneshopPages;

@Controller
@RequestMapping(value = "/logout")
public class LogoutController
{
	@GetMapping
	public String logOut()
	{
		return PhoneshopPages.UserPages.ProductListPage;
	}
}
