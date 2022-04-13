package by.bsuir.phoneshop.web.controller.pages;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import by.bsuir.phoneshop.core.beans.Phone;
import by.bsuir.phoneshop.core.service.PhoneService;
import by.bsuir.phoneshop.web.controller.constants.PhoneshopPages;

@Controller
public class HotPricesPageController
{
	private static final int AMOUNT_TO_GET = 10;

	@Resource
	private PhoneService phoneServiceImpl;

	@GetMapping("/hot-prices")
	public String getHotPricesPage(final Model model)
	{
		final List<Phone> phonesWithHotPrices = phoneServiceImpl.getHotPricedPhones(AMOUNT_TO_GET);

		if (phonesWithHotPrices != null && !CollectionUtils.isEmpty(phonesWithHotPrices))
		{
			model.addAttribute("phones", phonesWithHotPrices);
		}
		else
		{
			model.addAttribute("isEmptyPhoneList", true);
		}

		return PhoneshopPages.UserPages.HotPricesPage;
	}
}
