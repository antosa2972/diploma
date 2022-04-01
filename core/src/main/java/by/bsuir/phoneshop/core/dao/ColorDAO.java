package by.bsuir.phoneshop.core.dao;

import java.util.Optional;

import by.bsuir.phoneshop.core.beans.Color;

public interface ColorDAO
{
	Optional<Color> get(final Long key);

	void save(final Color color);
}
