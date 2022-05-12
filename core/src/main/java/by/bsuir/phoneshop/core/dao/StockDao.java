package by.bsuir.phoneshop.core.dao;

import java.util.Optional;

import by.bsuir.phoneshop.core.beans.Stock;

public interface StockDao
{
	Optional<Stock> get(final Long key);

	void update(final Long key, final Long stock, final Long reserved,final boolean isNewPhone);
}
