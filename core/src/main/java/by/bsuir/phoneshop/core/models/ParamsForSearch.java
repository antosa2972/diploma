package by.bsuir.phoneshop.core.models;

public class ParamsForSearch
{
	private String search;
	private String sortField;
	private String order;
	private int offset;
	private int limit;

	public ParamsForSearch(final String search, final String sortField, final String order, final int offset, final int limit)
	{
		this.search = search;
		this.sortField = sortField;
		this.order = order;
		this.offset = offset;
		this.limit = limit;
	}

	public String getSearch()
	{
		return search;
	}

	public void setSearch(final String search)
	{
		this.search = search;
	}

	public String getSortField()
	{
		return sortField;
	}

	public void setSortField(final String sortField)
	{
		this.sortField = sortField;
	}

	public String getOrder()
	{
		return order;
	}

	public void setOrder(final String order)
	{
		this.order = order;
	}

	public int getOffset()
	{
		return offset;
	}

	public void setOffset(final int offset)
	{
		this.offset = offset;
	}

	public int getLimit()
	{
		return limit;
	}

	public void setLimit(final int limit)
	{
		this.limit = limit;
	}
}
