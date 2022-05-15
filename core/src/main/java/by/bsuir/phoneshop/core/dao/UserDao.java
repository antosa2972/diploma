package by.bsuir.phoneshop.core.dao;

import java.util.Optional;

import by.bsuir.phoneshop.core.beans.User;

public interface UserDao
{
	Optional<User> getUserByUsername(final String username);
}
