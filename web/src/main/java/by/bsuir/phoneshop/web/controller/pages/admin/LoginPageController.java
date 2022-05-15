package by.bsuir.phoneshop.web.controller.pages.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.bsuir.phoneshop.web.controller.constants.PhoneshopPages;


@Controller
@RequestMapping("/login")
public class LoginPageController
{

	@Resource
	private Environment environment;

	@GetMapping
	public String getLoginPage(final @RequestParam(required = false) String error, final Authentication authentication,
										final Model model, final HttpSession httpSession)
	{
		if (authentication != null && authentication.isAuthenticated())
		{
			return "redirect:/orders";
		}
		if (error != null)
		{
			model.addAttribute("error", environment.getProperty("invalidCredentials"));
		}
		return PhoneshopPages.UserPages.LoginPage;
	}
}
