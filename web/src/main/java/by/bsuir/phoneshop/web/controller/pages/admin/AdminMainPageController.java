package by.bsuir.phoneshop.web.controller.pages.admin;

import java.security.Principal;

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
	public String showMainAdminPage(final Model model, final Principal principal)
	{
		model.addAttribute("username", principal.getName());
		return PhoneshopPages.AdminPages.AdminMainPage;
	}

	@GetMapping(value = "/device-operations")
	public String showDeviceOperationsPage()
	{
		return PhoneshopPages.AdminPages.ChooseOperationPage;
	}
}
