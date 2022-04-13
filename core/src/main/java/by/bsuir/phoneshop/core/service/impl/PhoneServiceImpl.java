package by.bsuir.phoneshop.core.service.impl;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import by.bsuir.phoneshop.core.beans.ParamsForSearch;
import by.bsuir.phoneshop.core.beans.Phone;
import by.bsuir.phoneshop.core.dao.PhoneDao;
import by.bsuir.phoneshop.core.service.PhoneService;

@Component
public class PhoneServiceImpl implements PhoneService
{
	@Resource
	private PhoneDao jdbcPhoneDao;

	@Override
	public Optional<Phone> getPhoneByKey(final Long key)
	{
		return jdbcPhoneDao.get(key);
	}

	@Override
	public Optional<Phone> getPhoneByModel(final String model)
	{
		return jdbcPhoneDao.get(model);
	}

	@Override
	public void savePhone(final Phone phone)
	{
		jdbcPhoneDao.save(phone);
	}

	@Override
	public List<Phone> getPhones(final int offset, final int limit)
	{
		return jdbcPhoneDao.findAll(offset, limit);
	}

	@Override
	public List<Phone> getPhones(final ParamsForSearch paramsForSearch)
	{
		return jdbcPhoneDao.findAll(paramsForSearch);
	}

	@Override
	public Long countPhoneQuantity(final ParamsForSearch paramsForSearch)
	{
		return jdbcPhoneDao.count(paramsForSearch);
	}

	@Override
	public List<Phone> getHotPricedPhones(final int amount)
	{
		return jdbcPhoneDao.findMaxDiscountPercentPhones(amount);
	}
}
