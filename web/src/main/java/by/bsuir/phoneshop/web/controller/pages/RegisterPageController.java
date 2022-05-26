package by.bsuir.phoneshop.web.controller.pages;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.bsuir.phoneshop.core.service.UserService;
import by.bsuir.phoneshop.web.controller.constants.PhoneshopPages;

@Controller
@RequestMapping(value = "/register")
public class RegisterPageController
{
	@Resource
	private UserService userServiceImpl;

	@GetMapping
	public String showRegisterPage()
	{
		return PhoneshopPages.UserPages.RegisterPage;
	}

	@PostMapping
	public String registerUser(final @RequestParam String username, @RequestParam final String password1, final Model model)
	{
		if (username == null || username.isEmpty() || password1 == null || password1.isEmpty() || username.length() < 5 || password1.length() < 5)
		{
			model.addAttribute("errorEmpty", true);
			return PhoneshopPages.UserPages.RegisterPage;
		}
		try
		{
			userServiceImpl.registerUser(username, password1);
		}
		catch (final Exception e)
		{
			model.addAttribute("error", true);
			return PhoneshopPages.UserPages.RegisterPage;
		}
		model.addAttribute("isRegistered", true);
		return PhoneshopPages.UserPages.LoginPage;
	}
}
