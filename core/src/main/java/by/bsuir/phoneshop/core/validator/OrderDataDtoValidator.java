package by.bsuir.phoneshop.core.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import by.bsuir.phoneshop.core.dto.OrderDataDto;

@Service
public class OrderDataDtoValidator implements Validator
{

	private static final String REGEX_PHONE = "^\\\\+\\\\d{12}$";


	@Override
	public boolean supports(final Class<?> aClass)
	{
		return OrderDataDto.class.equals(aClass);
	}

	@Override
	public void validate(final Object o, final Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "Empty first name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Empty last name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "Empty address");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "Empty phone field");
		if (!errors.hasErrors())
		{
			OrderDataDto orderDataDto = (OrderDataDto) o;
			if (validateWithRegex(orderDataDto.getPhone()))
			{
				errors.rejectValue("phone", "phone");
			}
		}
	}

	private boolean validateWithRegex(final String parameter)
	{
		Pattern pattern = Pattern.compile(OrderDataDtoValidator.REGEX_PHONE, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(parameter);
		return matcher.find();
	}
}
