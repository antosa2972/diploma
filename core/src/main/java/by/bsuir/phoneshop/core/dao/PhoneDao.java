package by.bsuir.phoneshop.core.dao;

import java.util.List;
import java.util.Optional;

import by.bsuir.phoneshop.core.beans.ParamsForSearch;
import by.bsuir.phoneshop.core.beans.Phone;

public interface PhoneDao
{
	Optional<Phone> get(Long key);

	Optional<Phone> get(String model);

	void save(Phone phone);

	List<Phone> findAll(int offset, int limit);

	List<Phone> findAll(ParamsForSearch paramsForSearch);

	Long count(ParamsForSearch paramsForSearch);
}
