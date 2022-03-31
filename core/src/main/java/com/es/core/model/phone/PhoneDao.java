package com.es.core.model.phone;

import java.util.List;
import java.util.Optional;

import com.es.core.model.ParamsForSearch;

public interface PhoneDao
{
	Optional<Phone> get(Long key);

	Optional<Phone> get(String model);

	void save(Phone phone);

	List<Phone> findAll(int offset, int limit);

	List<Phone> findAll(ParamsForSearch paramsForSearch);

	Long count(ParamsForSearch paramsForSearch);
}
