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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import by.bsuir.phoneshop.core.beans.Color;
import by.bsuir.phoneshop.core.beans.Phone;
import by.bsuir.phoneshop.core.dao.StockDao;
import by.bsuir.phoneshop.core.dto.PhoneAddDto;
import by.bsuir.phoneshop.core.service.PhoneService;
import by.bsuir.phoneshop.web.controller.constants.PhoneshopPages;

@Controller
@RequestMapping(value = "/admin/device-operations/update-delete/update/{id}")
public class UpdateDeviceController
{
	@Resource
	private PhoneService phoneServiceImpl;

	@Resource(name = "phoneAddDtoValidator")
	private Validator phoneAddDtoValidator;

	@Resource
	private StockDao jdbcStockDao;

	@Resource
	private MessageSource messageSource;

	@GetMapping
	public String showUpdatePage(final Model model, final @PathVariable Long id)
	{
		final Phone phone = phoneServiceImpl.getPhoneByKey(id).orElse(null);

		if (phone == null)
		{
			throw new RuntimeException();
		}
		else
		{
			model.addAttribute("isUpdatePage", true);
			model.addAttribute("recentData", phone);
			model.addAttribute("id", phone.getId());

			StringBuilder colors = new StringBuilder("");
			for (final Color color : phone.getColors())
			{
				colors.append(color.getId()).append(",");
			}
			colors.replace(colors.length() - 1, colors.length(), "");
			model.addAttribute("quantity", jdbcStockDao.get(phone.getId()).get());
			model.addAttribute("colors", colors.toString());
		}
		return PhoneshopPages.AdminPages.AddDevicePage;
	}

	@PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String updateDeviceInDb(final @Validated @ModelAttribute(name = "phoneAddDto") PhoneAddDto phoneAddDto, final BindingResult bindingResult, final Model model, @PathVariable Long id, final RedirectAttributes redirectAttributes)
	{
		phoneAddDtoValidator.validate(phoneAddDto, bindingResult);

		final String[] stringColors = phoneAddDto.getColors().split(",");
		if (bindingResult.hasErrors())
		{
			model.addAttribute("recentData", phoneAddDto);
			model.addAttribute("errors", bindingResult);
			model.addAttribute("error", messageSource.getMessage("page.admin.error.while.updating.device.to.db", null, LocaleContextHolder.getLocale()));
			return PhoneshopPages.AdminPages.AddDevicePage;
		}
		phoneServiceImpl.updateDevice(phoneServiceImpl.populateFields(phoneAddDto), phoneAddDto.getQuantity(), stringColors, id);
		redirectAttributes.addFlashAttribute("success", messageSource.getMessage("page.admin.success.while.updating.device.to.db", null, LocaleContextHolder.getLocale()));
		return "redirect:/admin/device-operations/update-delete/update/" + id;
	}

	@ExceptionHandler(Exception.class)
	public String handleOutOfStock()
	{
		return "redirect:/404?message=" + messageSource.getMessage("page.admin.error.updating", null, LocaleContextHolder.getLocale());
	}
}
