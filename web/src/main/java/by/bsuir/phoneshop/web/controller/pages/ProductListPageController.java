package by.bsuir.phoneshop.web.controller.pages;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import by.bsuir.phoneshop.core.beans.ParamsForSearch;
import by.bsuir.phoneshop.core.beans.Phone;
import by.bsuir.phoneshop.core.enums.SortField;
import by.bsuir.phoneshop.core.enums.SortOrder;
import by.bsuir.phoneshop.core.service.CartService;
import by.bsuir.phoneshop.core.service.PhoneService;
import by.bsuir.phoneshop.web.controller.constants.PhoneshopPages;

@Controller
@RequestMapping(value = "/productList")
public class ProductListPageController
{
	public static final Long QUANTITY_ON_PAGE = 20L;
	@Resource
	private PhoneService phoneServiceImpl;

	@Resource
	private CartService cartService;

	@Resource
	private HttpSession httpSession;

	@RequestMapping(method = RequestMethod.GET)
	public String showProductList(final @RequestParam(required = false) String search,
											@RequestParam(required = false) String field,
											@RequestParam(required = false) String order,
											@RequestParam(required = false) Long page, final Model model)
	{
		if (page == null)
		{
			page = 1L;
		}
		if (field != null && order != null)
		{
			try
			{
				field = SortField.valueOf(field.toUpperCase(Locale.ROOT)).name();
				order = SortOrder.valueOf(order.toUpperCase(Locale.ROOT)).name();
			}
			catch (IllegalArgumentException e)
			{
				field = null;
				order = null;
			}
		}
		long offset = (page - 1) * QUANTITY_ON_PAGE;
		final ParamsForSearch paramsForSearch = new ParamsForSearch(search, field, order, (int) offset,
					 QUANTITY_ON_PAGE.intValue());


		final List<Phone> phoneList = phoneServiceImpl.getPhones(paramsForSearch);

		final Long phoneQuantity = phoneServiceImpl.countPhoneQuantity(paramsForSearch);

		long numOfPages = phoneQuantity / QUANTITY_ON_PAGE;

		long lastPage;

		if (phoneQuantity % QUANTITY_ON_PAGE != 0)
		{
			lastPage = numOfPages + 1;
		}
		else
		{
			lastPage = numOfPages;
		}

		model.addAttribute("phones", phoneList);
		model.addAttribute("cart", cartService.getCart(httpSession));
		model.addAttribute("pages", lastPage);
		model.addAttribute("phoneQuantity", phoneQuantity);

		return PhoneshopPages.UserPages.ProductListPage;
	}
}
