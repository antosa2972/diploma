package by.bsuir.phoneshop.core.beans;

import java.math.BigDecimal;

public class CartItem
{
	private Phone phone;
	private Long quantity;
	private BigDecimal price;

	public CartItem(final Phone phone, final Long quantity, final BigDecimal price)
	{
		this.phone = phone;
		this.quantity = quantity;
		this.price = price;
	}

	public Phone getPhone()
	{
		return phone;
	}

	public void setPhone(Phone phone)
	{
		this.phone = phone;
	}

	public Long getQuantity()
	{
		return quantity;
	}

	public void setQuantity(Long quantity)
	{
		this.quantity = quantity;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}
}
