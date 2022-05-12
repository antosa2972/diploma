package by.bsuir.phoneshop.core.service;

import java.util.List;
import java.util.Optional;

import by.bsuir.phoneshop.core.beans.ParamsForSearch;
import by.bsuir.phoneshop.core.beans.Phone;

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
}
