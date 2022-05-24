package by.bsuir.phoneshop.web.controller.pages;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import by.bsuir.phoneshop.core.service.UserService;
import by.bsuir.phoneshop.web.controller.constants.PhoneshopPages;

@Controller
@RequestMapping(value = "/about-us")
public class AboutUsPageController
{
	@Resource
	private UserService userService;
	@GetMapping
	public String showAboutUsPage(final Model model)
	{
		model.addAttribute("employees", userService.getEmployees());
		return PhoneshopPages.UserPages.AboutUsPage;
	}
}
