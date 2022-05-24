package by.bsuir.phoneshop.core.service;

import java.util.List;
import java.util.Optional;

import by.bsuir.phoneshop.core.models.ParamsForSearch;
import by.bsuir.phoneshop.core.models.Phone;
import by.bsuir.phoneshop.core.dto.PhoneAddDto;

public interface PhoneService
{
	Optional<Phone> getPhoneByKey(final Long key);

	Optional<Phone> getPhoneByModel(final String model);

	void savePhone(final Phone phone, Long stock, final String[] colors);

	List<Phone> getPhones(final int offset, final int limit);

	List<Phone> getPhones(final ParamsForSearch paramsForSearch);

	Long countPhoneQuantity(final ParamsForSearch paramsForSearch);

	List<Phone> getHotPricedPhones(final int amount);

	void deletePhone(final Long id);

	Phone populateFields(final PhoneAddDto phoneAddDto);

	void updateDevice(final Phone phone,final Long stock, final String[] colors, final Long id);
}
