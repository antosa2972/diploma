package by.bsuir.phoneshop.core.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import by.bsuir.phoneshop.core.enums.OrderStatus;

public class Order
{
	private Long id;
	private List<OrderItem> orderItems;
	/**
	 * A sum of order item prices;
	 */
	private BigDecimal subtotal;
	private BigDecimal deliveryPrice;
	/**
	 * <code>subtotal</code> + <code>deliveryPrice</code>
	 */
	private BigDecimal totalPrice;

	private String firstName;
	private String lastName;
	private String deliveryAddress;
	private String contactPhoneNo;
	private String additionalInfo;
	private Timestamp date;

	private OrderStatus status;

	public Order()
	{
		orderItems = new ArrayList<>();
		subtotal = BigDecimal.ZERO;
		totalPrice = BigDecimal.ZERO;
		deliveryPrice = BigDecimal.ZERO;
		date = Timestamp.from(Instant.now());
	}

	public Long getId()
	{
		return id;
	}

	public void setId(final Long id)
	{
		this.id = id;
	}

	public List<OrderItem> getOrderItems()
	{
		return orderItems;
	}

	public void setOrderItems(final List<OrderItem> orderItems)
	{
		this.orderItems = orderItems;
	}

	public BigDecimal getSubtotal()
	{
		return subtotal;
	}

	public void setSubtotal(final BigDecimal subtotal)
	{
		this.subtotal = subtotal;
	}

	public BigDecimal getDeliveryPrice()
	{
		return deliveryPrice;
	}

	public void setDeliveryPrice(final BigDecimal deliveryPrice)
	{
		this.deliveryPrice = deliveryPrice;
	}

	public BigDecimal getTotalPrice()
	{
		return totalPrice;
	}

	public void setTotalPrice(final BigDecimal totalPrice)
	{
		this.totalPrice = totalPrice;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}

	public String getDeliveryAddress()
	{
		return deliveryAddress;
	}

	public void setDeliveryAddress(final String deliveryAddress)
	{
		this.deliveryAddress = deliveryAddress;
	}

	public String getContactPhoneNo()
	{
		return contactPhoneNo;
	}

	public void setContactPhoneNo(final String contactPhoneNo)
	{
		this.contactPhoneNo = contactPhoneNo;
	}

	public OrderStatus getStatus()
	{
		return status;
	}

	public Timestamp getDate()
	{
		return date;
	}

	public void setDate(final Timestamp date)
	{
		this.date = date;
	}

	public void setStatus(final OrderStatus status)
	{
		this.status = status;
	}

	public String getAdditionalInfo()
	{
		return additionalInfo;
	}

	public void setAdditionalInfo(final String additionalInfo)
	{
		this.additionalInfo = additionalInfo;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + id.hashCode() + date.hashCode() + totalPrice.hashCode();
		return result;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (obj == this)
		{
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass())
		{
			return false;
		}
		Order order = (Order) obj;
		return this.id.equals(order.id);
	}
}
