package by.bsuir.phoneshop.core.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart
{
	private List<CartItem> cartItems;
	private Long totalQuantity;
	private BigDecimal totalCost;

	public Cart()
	{
		cartItems = new ArrayList<>();
		totalQuantity = 0L;
		totalCost = BigDecimal.ZERO;
	}

	public List<CartItem> getCartItems()
	{
		return cartItems;
	}

	public void setCartItems(final List<CartItem> cartItems)
	{
		this.cartItems = cartItems;
	}

	public Long getTotalQuantity()
	{
		return totalQuantity;
	}

	public void setTotalQuantity(final Long totalQuantity)
	{
		this.totalQuantity = totalQuantity;
	}

	public BigDecimal getTotalCost()
	{
		return totalCost;
	}

	public void setTotalCost(final BigDecimal totalCost)
	{
		this.totalCost = totalCost;
	}
}
