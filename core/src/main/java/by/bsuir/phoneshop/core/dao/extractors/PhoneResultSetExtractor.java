package by.bsuir.phoneshop.core.dao.extractors;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import by.bsuir.phoneshop.core.beans.Color;
import by.bsuir.phoneshop.core.beans.Phone;

@Component
public class PhoneResultSetExtractor implements ResultSetExtractor<List<Phone>>
{
	@Override
	public List<Phone> extractData(final ResultSet resultSet) throws SQLException, DataAccessException
	{
		final Map<Phone, Set<Color>> data = new LinkedHashMap<>();
		while (resultSet.next())
		{
			final Phone phone = configurePhoneFields(resultSet);

			data.putIfAbsent(phone, new HashSet<>());
			final Color color = new Color();
			color.setId(resultSet.getLong("colors.id"));
			color.setCode(resultSet.getString("code"));
			data.get(phone).add(color);
		}
		final List<Phone> phoneList = new ArrayList<>();
		for (Map.Entry<Phone, Set<Color>> entry : data.entrySet())
		{
			final Phone phoneToAdd = entry.getKey();
			phoneToAdd.setColors(entry.getValue());
			phoneList.add(phoneToAdd);
		}
		return phoneList;
	}

	private Phone configurePhoneFields(final ResultSet resultSet) throws SQLException
	{
		final Phone phone = new Phone();

		phone.setId(resultSet.getLong("id"));
		phone.setBrand(resultSet.getString("brand"));
		phone.setModel(resultSet.getString("model"));
		phone.setPrice(BigDecimal.valueOf(resultSet.getLong("price")));
		phone.setDisplaySizeInches(BigDecimal.valueOf(resultSet.getLong("displaySizeInches")));
		phone.setWeightGr(resultSet.getInt("weightGr"));
		phone.setWidthMm(BigDecimal.valueOf(resultSet.getFloat("lengthMm")));
		phone.setHeightMm(BigDecimal.valueOf(resultSet.getFloat("heightMm")));
		phone.setAnnounced(resultSet.getDate("announced"));
		phone.setDeviceType(resultSet.getString("deviceType"));
		phone.setOs(resultSet.getString("os"));
		phone.setDisplayResolution(resultSet.getString("displayResolution"));
		phone.setPixelDensity(resultSet.getInt("pixelDensity"));
		phone.setDisplayTechnology(resultSet.getString("displayTechnology"));
		phone.setBackCameraMegapixels(BigDecimal.valueOf(resultSet.getFloat("backCameraMegapixels")));
		phone.setFrontCameraMegapixels(BigDecimal.valueOf(resultSet.getFloat("frontCameraMegapixels")));
		phone.setRamGb(BigDecimal.valueOf(resultSet.getFloat("ramGb")));
		phone.setInternalStorageGb(BigDecimal.valueOf(resultSet.getFloat("internalStorageGb")));
		phone.setBatteryCapacityMah(resultSet.getInt("batteryCapacityMah"));
		phone.setTalkTimeHours(BigDecimal.valueOf(resultSet.getFloat("talkTimeHours")));
		phone.setStandByTimeHours(BigDecimal.valueOf(resultSet.getFloat("standByTimeHours")));
		phone.setBluetooth(resultSet.getString("bluetooth"));
		phone.setPositioning(resultSet.getString("positioning"));
		phone.setPositioning(resultSet.getString("positioning"));
		phone.setImageUrl(resultSet.getString("imageUrl"));
		phone.setDescription(resultSet.getString("description"));

		return phone;
	}
}
