package by.bsuir.phoneshop.web.controller.pages.admin.devicemanagement;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import by.bsuir.phoneshop.core.beans.ParamsForSearch;
import by.bsuir.phoneshop.core.beans.Phone;
import by.bsuir.phoneshop.core.beans.errors.ResponseErrors;
import by.bsuir.phoneshop.core.service.PhoneService;
import by.bsuir.phoneshop.web.controller.constants.PhoneshopPages;

@Controller
@RequestMapping(value = "/admin/device-operations/update-delete")
public class UpdateDeleteDeviceController
{

	@Resource
	private PhoneService phoneServiceImpl;

	private static final Long QUANTITY_ON_PAGE = 10L;

	@GetMapping
	public String showDeleteUpdatePage(final String search, Long page, final Model model)
	{
		if (page == null)
		{
			page = 1L;
		}
		long offset = (page - 1) * QUANTITY_ON_PAGE;
		final ParamsForSearch paramsForSearch = new ParamsForSearch(search, null, null, (int) offset,
					 QUANTITY_ON_PAGE.intValue());

		final List<Phone> phoneList = phoneServiceImpl.getPhones(paramsForSearch);

		final Long phoneQuantity = phoneServiceImpl.countPhoneQuantity(paramsForSearch);

		long numOfPages = phoneQuantity / QUANTITY_ON_PAGE;

		long lastPage;

		if (phoneQuantity % QUANTITY_ON_PAGE != 0)
		{
			lastPage = numOfPages + 2;
		}
		else
		{
			lastPage = numOfPages + 1;
		}

		model.addAttribute("phones", phoneList);
		model.addAttribute("pages", lastPage);
		model.addAttribute("phoneQuantity", phoneQuantity);

		return PhoneshopPages.AdminPages.UpdateDeletePage;
	}

	@PostMapping(value = "/delete", consumes = "application/json")
	public ResponseEntity<?> delete(final @RequestBody Long id, final BindingResult bindingResult)
	{
		try
		{
			phoneServiceImpl.deletePhone(id);

			return ResponseEntity.ok().build();
		}
		catch (Exception e)
		{
			final ResponseErrors errors = new ResponseErrors(e.getMessage());
			return ResponseEntity.badRequest().body(errors);
		}
	}
}
