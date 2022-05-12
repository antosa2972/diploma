package by.bsuir.phoneshop.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import by.bsuir.phoneshop.core.beans.ParamsForSearch;
import by.bsuir.phoneshop.core.beans.Phone;
import by.bsuir.phoneshop.core.dao.PhoneDao;
import by.bsuir.phoneshop.core.dao.StockDao;
import by.bsuir.phoneshop.core.service.PhoneService;

@Component
public class PhoneServiceImpl implements PhoneService
{
	@Resource
	private PhoneDao jdbcPhoneDao;

	@Resource
	private StockDao jdbcStockDao;

	@Resource
	private JdbcTemplate jdbcTemplate;

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

	public void savePhone(final Phone phone, final Long quantity, final String[] colors)
	{
		jdbcPhoneDao.save(phone, colors);

		final Phone savedPhone = getPhoneByModel(phone.getModel()).get();

		jdbcStockDao.update(savedPhone.getId(), quantity, 0L, true);

		for (final String color : colors)
		{
			jdbcTemplate.update("insert into phone2color(phoneId,colorId) values (?,?)", savedPhone.getId(), color);
		}
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
		final List<Phone> phones = jdbcPhoneDao.findMaxDiscountPercentPhones();
		final List<Phone> limitedPhoneList = new ArrayList<>();
		for (int i = 0; i < phones.size(); i++)
		{
			if (i < amount)
			{
				limitedPhoneList.add(phones.get(i));
			}
			else
			{
				break;
			}
		}
		return limitedPhoneList;
	}

	@Override
	public void deletePhone(final Long id)
	{
		jdbcPhoneDao.deletePhone(id);
	}
}
