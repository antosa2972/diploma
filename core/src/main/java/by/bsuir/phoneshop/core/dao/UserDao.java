package by.bsuir.phoneshop.core.dao;

import java.util.List;
import java.util.Optional;

import by.bsuir.phoneshop.core.models.Employee;
import by.bsuir.phoneshop.core.models.User;

public interface UserDao
{
	Optional<User> getUserByUsername(final String username);

	List<User> getUsersExceptAdmin(int limit, int offset);

	void updateUserStatus(final int status,final String username);

	void addNewUserToDb(final User user);

	List<Employee> getMainEmployees();
}
