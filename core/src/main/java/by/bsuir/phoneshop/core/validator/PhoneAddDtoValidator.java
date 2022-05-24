package by.bsuir.phoneshop.core.validator;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import by.bsuir.phoneshop.core.dto.PhoneAddDto;

@Service
public class PhoneAddDtoValidator implements Validator
{
	@Override
	public boolean supports(Class<?> aClass)
	{
		return PhoneAddDto.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "brand", "page.admin.validation.empty.brand");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "model", "page.admin.validation.empty.brand");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "page.admin.validation.empty.price");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "imageUrl", "page.admin.validation.empty.imageUrl");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "page.admin.validation.empty.quantity");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "page.admin.validation.empty.description");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "displaySizeInches", "page.admin.validation.empty.displaySizeInches");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "displayResolution", "page.admin.validation.empty.displayResolution");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pixelDensity", "page.admin.validation.empty.pixelDensity");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "displayTechnology", "page.admin.validation.empty.displayTechnology");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "weightGr", "page.admin.validation.empty.weightGr");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lengthMm", "page.admin.validation.empty.lengthMm");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "widthMm", "page.admin.validation.empty.widthMm");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "colors", "page.admin.validation.empty.colors");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deviceType", "page.admin.validation.empty.deviceType");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "backCameraMegapixels", "page.admin.validation.empty.backCameraMegapixels");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "frontCameraMegapixels", "page.admin.validation.empty.frontCameraMegapixels");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "batteryCapacityMah", "page.admin.validation.empty.batteryCapacityMah");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "talkTimeHours", "page.admin.validation.empty.talkTimeHours");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "standByTimeHours", "page.admin.validation.empty.standByTimeHours");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bluetooth", "page.admin.validation.empty.bluetooth");

		if (!errors.hasErrors())
		{
			final PhoneAddDto phoneAddDto = (PhoneAddDto) o;

			if (phoneAddDto.getPrice() == null || phoneAddDto.getPrice().compareTo(BigDecimal.valueOf(0L)) <= 0)
			{
				errors.rejectValue("price", "page.admin.wrong.price");
			}
			if (phoneAddDto.getQuantity() == null || phoneAddDto.getQuantity() <= 0)
			{
				errors.rejectValue("quantity", "page.admin.wrong.quantity");
			}
			if (phoneAddDto.getDisplaySizeInches() == null || phoneAddDto.getDisplaySizeInches().compareTo(BigDecimal.ZERO) <= 0)
			{
				errors.rejectValue("displaySizeInches", "page.admin.wrong.displaySizeInches");
			}
			if (phoneAddDto.getWeightGr() == null || phoneAddDto.getWeightGr() <= 0)
			{
				errors.rejectValue("weightGr", "page.admin.wrong.weightGr");
			}
			if (phoneAddDto.getLengthMm() == null || phoneAddDto.getLengthMm().compareTo(BigDecimal.ZERO) <= 0)
			{
				errors.rejectValue("lengthMm", "page.admin.wrong.lengthMm");
			}
			if (phoneAddDto.getWidthMm() == null || phoneAddDto.getWidthMm().compareTo(BigDecimal.ZERO) <= 0)
			{
				errors.rejectValue("widthMm", "page.admin.wrong.widthMm");
			}
			if (phoneAddDto.getPixelDensity() == null || phoneAddDto.getPixelDensity() <= 0)
			{
				errors.rejectValue("pixelDensity", "page.admin.wrong.pixelDensity");
			}
			if (phoneAddDto.getBackCameraMegapixels() == null || phoneAddDto.getBackCameraMegapixels().compareTo(BigDecimal.ZERO) <= 0)
			{
				errors.rejectValue("backCameraMegapixels", "page.admin.wrong.backCameraMegapixels");
			}
			if (phoneAddDto.getFrontCameraMegapixels() == null || phoneAddDto.getFrontCameraMegapixels().compareTo(BigDecimal.ZERO) <= 0)
			{
				errors.rejectValue("frontCameraMegapixels", "page.admin.wrong.frontCameraMegapixels");
			}
			if (phoneAddDto.getBatteryCapacityMah() == null || phoneAddDto.getBatteryCapacityMah() <= 0)
			{
				errors.rejectValue("batteryCapacityMah", "page.admin.wrong.batteryCapacityMah");
			}
			if (phoneAddDto.getTalkTimeHours() == null || phoneAddDto.getTalkTimeHours().compareTo(BigDecimal.ZERO) <= 0)
			{
				errors.rejectValue("talkTimeHours", "page.admin.wrong.talkTimeHours");
			}
			if (phoneAddDto.getStandByTimeHours() == null || phoneAddDto.getStandByTimeHours().compareTo(BigDecimal.ZERO) <= 0)
			{
				errors.rejectValue("standByTimeHours", "page.admin.wrong.standByTimeHours");
			}
			if (!validateColorWithRegex(phoneAddDto.getColors()))
			{
				errors.rejectValue("colors", "bad.color");
			}

		}
	}

	private boolean validateColorWithRegex(final String parameter)
	{
		Pattern pattern = Pattern.compile("^\\d[\\d,]+?\\d$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(parameter);
		return matcher.find();
	}
}
