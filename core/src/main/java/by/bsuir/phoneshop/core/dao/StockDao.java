package by.bsuir.phoneshop.core.dao;

import java.util.Optional;

import by.bsuir.phoneshop.core.beans.Stock;

public interface StockDao
{
	Optional<Stock> get(Long key);

	void update(Long key, Long stock, Long reserved);
}
