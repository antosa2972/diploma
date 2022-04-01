package by.bsuir.phoneshop.core.beans;

public class Stock
{
	private Phone phone;
	private Integer stock;
	private Integer reserved;

	public Phone getPhone()
	{
		return phone;
	}

	public void setPhone(final Phone phone)
	{
		this.phone = phone;
	}

	public Integer getStock()
	{
		return stock;
	}

	public void setStock(final Integer stock)
	{
		this.stock = stock;
	}

	public Integer getReserved()
	{
		return reserved;
	}

	public void setReserved(final Integer reserved)
	{
		this.reserved = reserved;
	}
}
