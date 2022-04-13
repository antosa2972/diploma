package by.bsuir.phoneshop.web.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import by.bsuir.phoneshop.web.controller.constants.PhoneshopPages;

@Controller
@RequestMapping("/contacts")
public class ContactsPageController
{
	@GetMapping
	public String getContactsPage()
	{
		return PhoneshopPages.UserPages.ContactsPage;
	}
}
