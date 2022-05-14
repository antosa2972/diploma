package by.bsuir.phoneshop.core.dao;

import java.util.List;
import java.util.Optional;

import by.bsuir.phoneshop.core.beans.ParamsForSearch;
import by.bsuir.phoneshop.core.beans.Phone;

public interface PhoneDao
{
	Optional<Phone> get(final Long key);

	Optional<Phone> get(final String model);

	void save(final Phone phone);

	List<Phone> findAll(final int offset, final int limit);

	List<Phone> findAll(final ParamsForSearch paramsForSearch);

	Long count(final ParamsForSearch paramsForSearch);

	List<Phone> findMaxDiscountPercentPhones();

	void deletePhone(final Long id);

	void updateDevice(final Phone phone);
}
