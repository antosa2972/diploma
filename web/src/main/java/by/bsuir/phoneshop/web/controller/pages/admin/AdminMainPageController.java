package by.bsuir.phoneshop.web.controller.pages.admin;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import by.bsuir.phoneshop.web.controller.constants.PhoneshopPages;

@Controller
@RequestMapping(value = "/admin/main")
public class AdminMainPageController
{
	@GetMapping
	public String showMainAdminPage(final Model model, final Authentication principal)
	{
		model.addAttribute("username", principal.getName());
		return PhoneshopPages.UserPages.AdminMainPage;
	}
}
