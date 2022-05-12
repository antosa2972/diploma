package by.bsuir.phoneshop.core.dto;

import java.math.BigDecimal;

public class PhoneAddDto
{

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

	private String colors;

	private BigDecimal heightMm;

	private String deviceType;

	private BigDecimal backCameraMegapixels;

	private BigDecimal frontCameraMegapixels;

	private BigDecimal internalStorageGb;

	private Integer batteryCapacityMah;

	private BigDecimal talkTimeHours;

	private BigDecimal standByTimeHours;

	private String bluetooth;


	public String getBrand()
	{
		return brand;
	}

	public void setBrand(final String brand)
	{
		this.brand = brand;
	}

	public BigDecimal getDisplaySizeInches()
	{
		return displaySizeInches;
	}

	public void setDisplaySizeInches(final BigDecimal displaySizeInches)
	{
		this.displaySizeInches = displaySizeInches;
	}

	public Integer getWeightGr()
	{
		return weightGr;
	}

	public void setWeightGr(final Integer weightGr)
	{
		this.weightGr = weightGr;
	}

	public String getDeviceType()
	{
		return deviceType;
	}

	public void setDeviceType(final String deviceType)
	{
		this.deviceType = deviceType;
	}

	public String getDisplayResolution()
	{
		return displayResolution;
	}

	public void setDisplayResolution(final String displayResolution)
	{
		this.displayResolution = displayResolution;
	}

	public Integer getPixelDensity()
	{
		return pixelDensity;
	}

	public void setPixelDensity(final Integer pixelDensity)
	{
		this.pixelDensity = pixelDensity;
	}

	public String getDisplayTechnology()
	{
		return displayTechnology;
	}

	public void setDisplayTechnology(final String displayTechnology)
	{
		this.displayTechnology = displayTechnology;
	}

	public BigDecimal getBackCameraMegapixels()
	{
		return backCameraMegapixels;
	}

	public void setBackCameraMegapixels(final BigDecimal backCameraMegapixels)
	{
		this.backCameraMegapixels = backCameraMegapixels;
	}

	public BigDecimal getFrontCameraMegapixels()
	{
		return frontCameraMegapixels;
	}

	public void setFrontCameraMegapixels(final BigDecimal frontCameraMegapixels)
	{
		this.frontCameraMegapixels = frontCameraMegapixels;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(final String model)
	{
		this.model = model;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public void setPrice(final BigDecimal price)
	{
		this.price = price;
	}

	public BigDecimal getInternalStorageGb()
	{
		return internalStorageGb;
	}

	public void setInternalStorageGb(final BigDecimal internalStorageGb)
	{
		this.internalStorageGb = internalStorageGb;
	}

	public Integer getBatteryCapacityMah()
	{
		return batteryCapacityMah;
	}

	public void setBatteryCapacityMah(final Integer batteryCapacityMah)
	{
		this.batteryCapacityMah = batteryCapacityMah;
	}

	public BigDecimal getTalkTimeHours()
	{
		return talkTimeHours;
	}

	public void setTalkTimeHours(final BigDecimal talkTimeHours)
	{
		this.talkTimeHours = talkTimeHours;
	}

	public BigDecimal getStandByTimeHours()
	{
		return standByTimeHours;
	}

	public void setStandByTimeHours(final BigDecimal standByTimeHours)
	{
		this.standByTimeHours = standByTimeHours;
	}

	public String getBluetooth()
	{
		return bluetooth;
	}

	public void setBluetooth(final String bluetooth)
	{
		this.bluetooth = bluetooth;
	}

	public String getImageUrl()
	{
		return imageUrl;
	}

	public void setImageUrl(final String imageUrl)
	{
		this.imageUrl = imageUrl;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(final String description)
	{
		this.description = description;
	}

	public BigDecimal getLengthMm()
	{
		return lengthMm;
	}

	public void setLengthMm(final BigDecimal lengthMm)
	{
		this.lengthMm = lengthMm;
	}

	public BigDecimal getWidthMm()
	{
		return widthMm;
	}

	public void setWidthMm(final BigDecimal widthMm)
	{
		this.widthMm = widthMm;
	}

	public BigDecimal getHeightMm()
	{
		return heightMm;
	}

	public void setHeightMm(final BigDecimal heightMm)
	{
		this.heightMm = heightMm;
	}

	public Integer getDiscountPercent()
	{
		return discountPercent;
	}

	public void setDiscountPercent(Integer discountPercent)
	{
		this.discountPercent = discountPercent;
	}

	public Long getQuantity()
	{
		return quantity;
	}

	public void setQuantity(final Long quantity)
	{
		this.quantity = quantity;
	}

	public String getColors()
	{
		return colors;
	}

	public void setColors(String colors)
	{
		this.colors = colors;
	}
}
