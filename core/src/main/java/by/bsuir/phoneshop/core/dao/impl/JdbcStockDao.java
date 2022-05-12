package by.bsuir.phoneshop.core.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import by.bsuir.phoneshop.core.beans.Stock;
import by.bsuir.phoneshop.core.dao.StockDao;

@Component
public class JdbcStockDao implements StockDao
{
	@Resource
	private JdbcTemplate jdbcTemplate;

	private static final String SQL_GET_STOCK = "select * from stocks where phoneId = ";
	private static final String SQL_UPDATE = "update stocks set stock = %d, reserved = %d where phoneId = %d";
	private static final String SQL_INSERT = "insert into stocks(phoneId,stock,reserved) values (?,?,?)";

	@Override
	public Optional<Stock> get(final Long key)
	{
		final List<Stock> stocks = jdbcTemplate.query(SQL_GET_STOCK + key, new BeanPropertyRowMapper<>(Stock.class));
		return stocks.stream().findFirst();
	}

	@Override
	public void update(final Long key, final Long stock, final Long reserved, boolean isNewPhone)
	{
		String query;
		if (!isNewPhone)
		{
			query = String.format(SQL_UPDATE, stock, reserved, key);
			jdbcTemplate.update(query);

		}
		else
		{
			jdbcTemplate.update(SQL_INSERT, key, stock, reserved);
		}

	}
}
