package by.bsuir.phoneshop.core.exception.errors;

public class ResponseErrors
{
	private String errorMessage;

	public ResponseErrors(final String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	public String getErrorsMessage()
	{
		return errorMessage;
	}

	public void setErrorsMessage(final String errorMessage)
	{
		this.errorMessage = errorMessage;
	}
}