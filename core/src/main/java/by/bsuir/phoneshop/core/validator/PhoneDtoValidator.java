package by.bsuir.phoneshop.core.validator;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import by.bsuir.phoneshop.core.dto.PhoneDto;
import by.bsuir.phoneshop.core.models.Stock;
import by.bsuir.phoneshop.core.dao.StockDao;

@Service
public class PhoneDtoValidator implements Validator
{

	@Resource
	private StockDao jdbcStockDao;

	@Override
	public boolean supports(final Class<?> aClass)
	{
		return PhoneDto.class.equals(aClass);
	}

	@Override
	public void validate(final Object o, final Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "Empty quantity");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "Empty id");
		if (!errors.hasErrors())
		{
			final PhoneDto dto = (PhoneDto) o;
			final Stock stock = jdbcStockDao.get(dto.getId()).orElse(null);
			if (dto.getQuantity() <= 0)
			{
				errors.rejectValue("quantity", "Wrong input");
			}
			if (stock == null || dto.getQuantity() > (stock.getStock() - stock.getReserved()))
			{
				errors.rejectValue("quantity", "Out of stock!");
			}
		}
	}
}
