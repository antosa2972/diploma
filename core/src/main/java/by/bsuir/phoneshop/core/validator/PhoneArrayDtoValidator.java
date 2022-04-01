package by.bsuir.phoneshop.core.validator;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import by.bsuir.phoneshop.core.dto.PhoneArrayDto;
import by.bsuir.phoneshop.core.beans.Stock;
import by.bsuir.phoneshop.core.dao.StockDao;


@Service
public class PhoneArrayDtoValidator implements Validator
{
	@Resource
	private StockDao jdbcStockDao;

	@Override
	public boolean supports(final Class<?> aClass)
	{
		return PhoneArrayDto.class.equals(aClass);
	}

	@Override
	public void validate(final Object o, final Errors errors)
	{
		PhoneArrayDto dto = (PhoneArrayDto) o;
		for (int i = 0; i < dto.getQuantity().length; i++)
		{
			try
			{
				final Stock stock = jdbcStockDao.get(Long.parseLong(dto.getPhoneId()[i])).orElse(null);
				final long quantity = Long.parseLong(dto.getQuantity()[i]);
				if (quantity <= 0 || stock == null || quantity > (stock.getStock() - stock.getReserved()))
				{
					errors.rejectValue("quantity", dto.getPhoneId()[i]);
				}
			}
			catch (NumberFormatException e)
			{
				errors.rejectValue("quantity", dto.getPhoneId()[i]);
			}
		}
	}
}
