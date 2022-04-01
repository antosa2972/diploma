package by.bsuir.phoneshop.core.beans;

import by.bsuir.phoneshop.core.beans.Phone;

public class Stock
{
	private Phone phone;
	private Integer stock;
	private Integer reserved;

	public Phone getPhone()
	{
		return phone;
	}

	public void setPhone(Phone phone)
	{
		this.phone = phone;
	}

	public Integer getStock()
	{
		return stock;
	}

	public void setStock(Integer stock)
	{
		this.stock = stock;
	}

	public Integer getReserved()
	{
		return reserved;
	}

	public void setReserved(Integer reserved)
	{
		this.reserved = reserved;
	}
}
