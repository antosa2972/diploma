package by.bsuir.phoneshop.web.controller.pages.admin.devicemanagement;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import by.bsuir.phoneshop.web.controller.constants.PhoneshopPages;

@Controller
@RequestMapping("/admin/device-operations/update/{id}")
public class UpdateDeviceController
{
	@GetMapping
	public String showUpdatePage(final Model model)
	{

		return PhoneshopPages.AdminPages.AddDevicePage;
	}
}
