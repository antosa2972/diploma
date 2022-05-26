package by.bsuir.phoneshop.core.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import by.bsuir.phoneshop.core.models.Color;
import by.bsuir.phoneshop.core.models.ParamsForSearch;
import by.bsuir.phoneshop.core.models.Phone;
import by.bsuir.phoneshop.core.dao.ColorDAO;
import by.bsuir.phoneshop.core.dao.PhoneDao;
import by.bsuir.phoneshop.core.dao.extractors.PhoneResultSetExtractor;

@Component
public class JdbcPhoneDao implements PhoneDao
{
	private static final String SQL_INSERT_PHONE = "insert into phones (id,brand,model,price,discountPercent,displaySizeInches,weightGr," +
				 "lengthMm,widthMm,heightMm,deviceType,displayResolution,pixelDensity,displayTechnology," +
				 "backCameraMegapixels,frontCameraMegapixels,internalStorageGb,batteryCapacityMah,talkTimeHours," +
				 "standByTimeHours,bluetooth,imageUrl,description) values (:id,:brand,:model,:price,:discountPercent,:displaySizeInches,:weightGr," +
				 ":lengthMm,:widthMm,:heightMm,:deviceType,:displayResolution,:pixelDensity," +
				 ":displayTechnology,:backCameraMegapixels,:frontCameraMegapixels,:internalStorageGb," +
				 ":batteryCapacityMah,:talkTimeHours,:standByTimeHours,:bluetooth,:imageUrl,:description)";
	private static final String SQL_GET_ALL_PHONES = "select * from phones left join phone2color " +
				 "on phones.id=phone2color.phoneId left join colors on colors.id = phone2color.colorId ";
	private static final String SQL_GET_MAX_DISCOUNTED_PHONES = "select * from phones left join phone2color " +
				 "on phones.id=phone2color.phoneId left join colors on colors.id = phone2color.colorId where phones.discountPercent!=0  ORDER BY phones.discountPercent DESC";
	public static final String SQL_GET_PHONE = "select * from phones where id= ";
	private static final String SQL_SELECT_COUNT_FIND_ALL_EXTENDED = "select count(*) from phones ";
	private static final String SQL_WHERE_SEARCH = "where (phones.id in (select phoneId from stocks) and " +
				 "(SELECT STOCK FROM STOCKS WHERE PHONEID = PHONES.ID AND STOCK > 0) and price is not null) ";
	public static final String SQL_SELECT_COLOR_IDS = "select colorId from phone2color where phoneId= ";
	public static final String SQL_GET_PHONE_BY_MODEL = "select * from phones where phones.model = '";
	public static final String DELETE_PHONE_SQL = "delete from phones where phones.id=?";
	public static final String SQL_UPDATE_PHONE = "update phones set brand= :brand,model= :model,price= :price,discountPercent= :discountPercent," +
				 "displaySizeInches= :displaySizeInches,weightGr= :weightGr,lengthMm= :lengthMm,widthMm= :widthMm,heightMm= :heightMm,deviceType= :deviceType,displayResolution= :displayResolution,pixelDensity= :pixelDensity," +
				 "displayTechnology= :displayTechnology,backCameraMegapixels= :backCameraMegapixels,frontCameraMegapixels= :frontCameraMegapixels,internalStorageGb= :internalStorageGb,batteryCapacityMah= :batteryCapacityMah," +
				 "talkTimeHours= :talkTimeHours,standByTimeHours= :standByTimeHours,bluetooth= :bluetooth,imageUrl= :imageUrl,description= :description where id= :id";

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Resource
	private PhoneResultSetExtractor phoneResultSetExtractor;

	@Resource
	private ColorDAO jdbcColorDAO;

	@Override
	public Optional<Phone> get(final Long key)
	{
		final String query = SQL_GET_PHONE + key;
		return getPhoneAndColors(query);
	}

	@Override
	public Optional<Phone> get(final String model)
	{
		final String query = SQL_GET_PHONE_BY_MODEL + model + "'";
		return getPhoneAndColors(query);
	}

	private Optional<Phone> getPhoneAndColors(final String query)
	{
		Phone phone;
		try
		{
			phone = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Phone.class));
			phone.setActualPrice();
		}
		catch (EmptyResultDataAccessException e)
		{
			return Optional.empty();
		}
		final RowMapper<Long> idRowMapper = (rs, rowNum) -> rs.getLong("colorId");
		final String colorIdQuery = SQL_SELECT_COLOR_IDS + phone.getId();
		final List<Long> colorIds = jdbcTemplate.query(colorIdQuery, idRowMapper);

		if (colorIds != null && !colorIds.isEmpty())
		{
			final Set<Color> colorSet = new HashSet<>();
			for (Long colorId : colorIds)
			{
				final Optional<Color> colorOptional = jdbcColorDAO.get(colorId);
				colorOptional.ifPresent(colorSet::add);
			}
			phone.setColors(colorSet);
		}
		return Optional.ofNullable(phone);
	}

	@Override
	@Transactional
	public void save(final Phone phone)
	{
		namedParameterJdbcTemplate.update(SQL_INSERT_PHONE, populateNamedParams(phone));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Phone> findAll(final int offset, final int limit)
	{
		String query = SQL_GET_ALL_PHONES + " offset " + offset + " limit " + limit;
		return jdbcTemplate.query(query, phoneResultSetExtractor);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Phone> findAll(final ParamsForSearch paramsForSearch)
	{

		final String search = paramsForSearch.getSearch();
		final String sortField = paramsForSearch.getSortField();
		final String order = paramsForSearch.getOrder();
		final int offset = paramsForSearch.getOffset();
		final int limit = paramsForSearch.getLimit();
		final List<Object> objects = new ArrayList<>();
		final List<Integer> types = new ArrayList<>();

		String query = SQL_GET_ALL_PHONES + SQL_WHERE_SEARCH;

		if (search != null && !search.isEmpty())
		{
			query = query + "and lower(model) like lower(?) ";
			objects.add("%" + search + "%");
			types.add(Types.VARCHAR);
		}
		if (sortField != null && order != null)
		{
			query = query + String.format("group by phones.id,phone2color.colorId order by %s %s ", sortField, order);
		}

			query = query + " limit " + limit + " offset " + offset;

		final int[] typesArray = types.stream()
					 .mapToInt(i -> i)
					 .toArray();
		return jdbcTemplate.query(query, objects.toArray(), typesArray, phoneResultSetExtractor);
	}

	@Override
	public Long count(final ParamsForSearch paramsForSearch)
	{

		final String search = paramsForSearch.getSearch();

		String request = SQL_SELECT_COUNT_FIND_ALL_EXTENDED + SQL_WHERE_SEARCH;

		if (search != null && !search.isEmpty())
		{
			request = request + "and (lower(model) like lower(?))";
			return jdbcTemplate.queryForObject(request, new Object[]{"%" + search +
						 "%"}, new int[]{Types.VARCHAR}, Long.class);
		}
		else
		{
			return jdbcTemplate.queryForObject(request, Long.class);
		}
	}

	@Override
	public List<Phone> findMaxDiscountPercentPhones()
	{
		return jdbcTemplate.query(SQL_GET_MAX_DISCOUNTED_PHONES, phoneResultSetExtractor);
	}

	@Override
	public void deletePhone(final Long id)
	{
		jdbcTemplate.update(DELETE_PHONE_SQL, id);
	}

	@Override
	@Transactional
	public void updateDevice(final Phone phone)
	{
		namedParameterJdbcTemplate.update(SQL_UPDATE_PHONE, populateNamedParams(phone));
	}

	private MapSqlParameterSource populateNamedParams(final Phone phone)
	{
		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("id", phone.getId());
		in.addValue("brand", phone.getBrand());
		in.addValue("model", phone.getModel());
		in.addValue("price", phone.getPrice());
		in.addValue("discountPercent", phone.getDiscountPercent());
		in.addValue("displaySizeInches", phone.getDisplaySizeInches());
		in.addValue("weightGr", phone.getWeightGr());
		in.addValue("lengthMm", phone.getLengthMm());
		in.addValue("widthMm", phone.getWidthMm());
		in.addValue("heightMm", phone.getHeightMm());
		in.addValue("deviceType", phone.getDeviceType());
		in.addValue("displayResolution", phone.getDisplayResolution());
		in.addValue("pixelDensity", phone.getPixelDensity());
		in.addValue("displayTechnology", phone.getDisplayTechnology());
		in.addValue("backCameraMegapixels", phone.getBackCameraMegapixels());
		in.addValue("frontCameraMegapixels", phone.getFrontCameraMegapixels());
		in.addValue("internalStorageGb", phone.getInternalStorageGb());
		in.addValue("batteryCapacityMah", phone.getBatteryCapacityMah());
		in.addValue("talkTimeHours", phone.getTalkTimeHours());
		in.addValue("standByTimeHours", phone.getStandByTimeHours());
		in.addValue("bluetooth", phone.getBluetooth());
		in.addValue("imageUrl", phone.getImageUrl());
		in.addValue("description", phone.getDescription());

		return in;
	}
}





