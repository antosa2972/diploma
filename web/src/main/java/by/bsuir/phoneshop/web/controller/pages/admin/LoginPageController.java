package by.bsuir.phoneshop.web.controller.pages.admin;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.bsuir.phoneshop.web.controller.constants.PhoneshopPages;


@Controller
@RequestMapping("/login")
public class LoginPageController
{

	@Resource
	private MessageSource messageSource;

	@GetMapping
	public String getLoginPage(final @RequestParam(required = false) String error, final Authentication authentication,
										final Model model)
	{
		if (authentication != null && authentication.isAuthenticated())
		{
			return "redirect:/orders";
		}
		if (error != null)
		{
			model.addAttribute("error", messageSource.getMessage("invalidCredentials", null, LocaleContextHolder.getLocale()));
		}
		return PhoneshopPages.UserPages.LoginPage;
	}

	@PostMapping
	public String login(final Model model)
	{
		model.addAttribute("error", messageSource.getMessage("invalidCredentials", null, LocaleContextHolder.getLocale()));
		return PhoneshopPages.UserPages.LoginPage;
	}
}
