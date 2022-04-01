package by.bsuir.phoneshop.core.validator;


import java.util.List;

import org.springframework.validation.ObjectError;

public class ValidationErrors
{

	private List<ObjectError> errors;

	public ValidationErrors(List<ObjectError> errors)
	{
		this.errors = errors;
	}

	public List<ObjectError> getErrors()
	{
		return errors;
	}

	public void setErrors(List<ObjectError> errors)
	{
		this.errors = errors;
	}
}