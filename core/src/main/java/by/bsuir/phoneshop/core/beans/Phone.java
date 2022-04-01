package by.bsuir.phoneshop.core.beans;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

public class Phone
{
	private Long id;
	private String brand;
	private String model;
	private BigDecimal price;

	private BigDecimal displaySizeInches;

	private Integer weightGr;

	private BigDecimal lengthMm;

	private BigDecimal widthMm;

	private BigDecimal heightMm;

	private Date announced;

	private String deviceType;

	private String os;

	private Set<Color> colors = Collections.EMPTY_SET;

	private String displayResolution;

	private Integer pixelDensity;

	private String displayTechnology;

	private BigDecimal backCameraMegapixels;

	private BigDecimal frontCameraMegapixels;

	private BigDecimal ramGb;

	private BigDecimal internalStorageGb;

	private Integer batteryCapacityMah;

	private BigDecimal talkTimeHours;

	private BigDecimal standByTimeHours;

	private String bluetooth;

	private String positioning;

	private String imageUrl;

	private String description;

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

	public Date getAnnounced()
	{
		return announced;
	}

	public void setAnnounced(final Date announced)
	{
		this.announced = announced;
	}

	public String getDeviceType()
	{
		return deviceType;
	}

	public void setDeviceType(final String deviceType)
	{
		this.deviceType = deviceType;
	}

	public String getOs()
	{
		return os;
	}

	public void setOs(final String os)
	{
		this.os = os;
	}

	public Set<Color> getColors()
	{
		return colors;
	}

	public void setColors(final Set<Color> colors)
	{
		this.colors = colors;
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

	public BigDecimal getRamGb()
	{
		return ramGb;
	}

	public void setRamGb(final BigDecimal ramGb)
	{
		this.ramGb = ramGb;
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

	public String getPositioning()
	{
		return positioning;
	}

	public void setPositioning(final String positioning)
	{
		this.positioning = positioning;
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

	public Long getId()
	{
		return id;
	}

	public void setId(final Long id)
	{
		this.id = id;
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
