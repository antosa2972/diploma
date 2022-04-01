package by.bsuir.phoneshop.core.beans;

public class OrderItem
{
	private Long id;
	private Phone phone;
	private Order order;
	private Long quantity;

	public OrderItem(final Long id, final Phone phone, final Order order, final Long quantity)
	{
		this.id = id;
		this.phone = phone;
		this.order = order;
		this.quantity = quantity;
	}

	public OrderItem(final CartItem cartItem, final Order order)
	{
		this.phone = cartItem.getPhone();
		this.quantity = cartItem.getQuantity();
		this.order = order;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(final Long id)
	{
		this.id = id;
	}

	public Phone getPhone()
	{
		return phone;
	}

	public void setPhone(final Phone phone)
	{
		this.phone = phone;
	}

	public Order getOrder()
	{
		return order;
	}

	public void setOrder(final Order order)
	{
		this.order = order;
	}

	public Long getQuantity()
	{
		return quantity;
	}

	public void setQuantity(final Long quantity)
	{
		this.quantity = quantity;
	}
}
