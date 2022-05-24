package by.bsuir.phoneshop.core.models;

import java.math.BigDecimal;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Phone
{
	private Long id;

	private String brand;

	private String model;

	private BigDecimal price;

	private Integer discountPercent;

	private String imageUrl;

	private Long quantity;

	private String description;

	private BigDecimal displaySizeInches;

	private String displayResolution;

	private Integer pixelDensity;

	private String displayTechnology;

	private Integer weightGr;

	private BigDecimal lengthMm;

	private BigDecimal widthMm;

	private BigDecimal heightMm;

	private String deviceType;

	private BigDecimal backCameraMegapixels;

	private BigDecimal frontCameraMegapixels;

	private BigDecimal internalStorageGb;

	private Integer batteryCapacityMah;

	private BigDecimal talkTimeHours;

	private BigDecimal standByTimeHours;

	private String bluetooth;

	@Setter(AccessLevel.NONE)
	private BigDecimal actualPrice;

	private Set<Color> colors;

	public void setActualPrice()
	{
		if (discountPercent != null)
		{

			final float discountPercent = this.discountPercent;
			final BigDecimal discountAmount = (this.price).multiply(BigDecimal.valueOf(discountPercent / 100.0));
			this.actualPrice = this.price.subtract(discountAmount);
		}
		else
		{
			this.actualPrice = this.price;
		}
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + id.hashCode() + brand.hashCode() + model.hashCode();
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
		Phone phone = (Phone) obj;
		return this.id.equals(phone.id) && this.brand.equals(phone.brand) && this.model.equals(phone.model);
	}
}
