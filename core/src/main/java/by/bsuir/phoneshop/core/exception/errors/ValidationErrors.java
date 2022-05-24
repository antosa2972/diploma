package by.bsuir.phoneshop.core.exception.errors;


import java.util.List;

import org.springframework.validation.ObjectError;

public class ValidationErrors
{

	private List<ObjectError> errors;

	public ValidationErrors(final List<ObjectError> errors)
	{
		this.errors = errors;
	}

	public List<ObjectError> getErrors()
	{
		return errors;
	}

	public void setErrors(final List<ObjectError> errors)
	{
		this.errors = errors;
	}
}