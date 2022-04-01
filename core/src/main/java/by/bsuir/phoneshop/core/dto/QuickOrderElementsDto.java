package by.bsuir.phoneshop.core.dto;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.phoneshop.core.beans.QuickOrderElement;

public class QuickOrderElementsDto
{
	private List<QuickOrderElement> quickOrderElements;

	public QuickOrderElementsDto()
	{
		this.quickOrderElements = new ArrayList<QuickOrderElement>();
	}

	public List<QuickOrderElement> getQuickOrderElements()
	{
		return quickOrderElements;
	}

	public void setQuickOrderElements(final List<QuickOrderElement> quickOrderElements)
	{
		this.quickOrderElements = quickOrderElements;
	}
}
