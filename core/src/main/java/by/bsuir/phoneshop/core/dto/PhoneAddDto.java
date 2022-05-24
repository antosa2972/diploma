package by.bsuir.phoneshop.core.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
