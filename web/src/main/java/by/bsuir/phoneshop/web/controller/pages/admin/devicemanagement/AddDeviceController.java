package by.bsuir.phoneshop.web.controller.pages.admin.devicemanagement;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import by.bsuir.phoneshop.core.dto.PhoneAddDto;
import by.bsuir.phoneshop.core.service.PhoneService;
import by.bsuir.phoneshop.web.controller.constants.PhoneshopPages;

@Controller
@RequestMapping("/admin/device-operations/add")
public class AddDeviceController
{
	@Resource
	private PhoneService phoneServiceImpl;

	@Resource(name = "phoneAddDtoValidator")
	private Validator phoneAddDtoValidator;

	@Resource
	private MessageSource messageSource;

	@GetMapping
	public String showAddDevicePage(final Model model)
	{
		model.addAttribute("phoneAddDto", new PhoneAddDto());
		return PhoneshopPages.UserPages.AddDevicePage;
	}

	@PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String addDeviceToDataBase(final @Validated @ModelAttribute(name = "phoneAddDto") PhoneAddDto phoneAddDto, final BindingResult bindingResult, final Model model)
	{
		phoneAddDtoValidator.validate(phoneAddDto, bindingResult);
		final String[] stringColors = phoneAddDto.getColors().split(",");
		if (bindingResult.hasErrors())
		{
			model.addAttribute("recentData", phoneAddDto);
			model.addAttribute("errors", bindingResult);
			model.addAttribute("error", messageSource.getMessage("page.admin.error.while.adding.device.to.db", null, LocaleContextHolder.getLocale()));
			return PhoneshopPages.UserPages.AddDevicePage;
		}
		phoneServiceImpl.savePhone(phoneServiceImpl.populateFields(phoneAddDto), phoneAddDto.getQuantity(), stringColors);
		model.addAttribute("success",  messageSource.getMessage("page.admin.success.while.adding.device.to.db", null, LocaleContextHolder.getLocale()));
		return PhoneshopPages.UserPages.AddDevicePage;
	}
}
