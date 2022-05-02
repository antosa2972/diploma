package by.bsuir.phoneshop.web.controller.pages;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import by.bsuir.phoneshop.core.service.EmployeeService;
import by.bsuir.phoneshop.web.controller.constants.PhoneshopPages;

@Controller
@RequestMapping(value = "/about-us")
public class AboutUsPageController
{
	@Resource
	private EmployeeService employeeService;
	@GetMapping
	public String showAboutUsPage(final Model model)
	{
		model.addAttribute("employees", employeeService.getEmployees());
		return PhoneshopPages.UserPages.AboutUsPage;
	}
}
