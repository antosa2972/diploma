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

import by.bsuir.phoneshop.core.beans.Phone;
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
		return PhoneshopPages.AdminPages.AddDevicePage;
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
			return PhoneshopPages.AdminPages.AddDevicePage;
		}
		phoneServiceImpl.savePhone(populateFields(phoneAddDto), phoneAddDto.getQuantity(), stringColors);
		model.addAttribute("success",  messageSource.getMessage("page.admin.success.while.adding.device.to.db", null, LocaleContextHolder.getLocale()));
		return PhoneshopPages.AdminPages.AddDevicePage;
	}

	private Phone populateFields(final PhoneAddDto phoneAddDto)
	{
		final Phone phone = new Phone();
		phone.setBrand(phoneAddDto.getBrand());
		phone.setModel(phoneAddDto.getModel());
		phone.setPrice(phoneAddDto.getPrice());
		if (phoneAddDto.getDiscountPercent() != null)
		{
			phone.setDiscountPercent(phoneAddDto.getDiscountPercent());
		}
		else
		{
			phone.setDiscountPercent(0);
		}
		phone.setImageUrl(phoneAddDto.getImageUrl());
		phone.setDescription(phoneAddDto.getDescription());
		phone.setDisplaySizeInches(phoneAddDto.getDisplaySizeInches());
		phone.setDisplayResolution(phoneAddDto.getDisplayResolution());
		phone.setPixelDensity(phoneAddDto.getPixelDensity());
		phone.setDisplayTechnology(phoneAddDto.getDisplayTechnology());
		phone.setWeightGr(phoneAddDto.getWeightGr());
		phone.setLengthMm(phoneAddDto.getLengthMm());
		phone.setWidthMm(phoneAddDto.getWidthMm());
		phone.setHeightMm(phoneAddDto.getHeightMm());
		phone.setDeviceType(phoneAddDto.getDeviceType());
		phone.setBackCameraMegapixels(phoneAddDto.getBackCameraMegapixels());
		phone.setFrontCameraMegapixels(phoneAddDto.getFrontCameraMegapixels());
		phone.setInternalStorageGb(phoneAddDto.getInternalStorageGb());
		phone.setBatteryCapacityMah(phoneAddDto.getBatteryCapacityMah());
		phone.setTalkTimeHours(phoneAddDto.getTalkTimeHours());
		phone.setStandByTimeHours(phoneAddDto.getStandByTimeHours());
		phone.setBluetooth(phoneAddDto.getBluetooth());

		return phone;
	}
}
