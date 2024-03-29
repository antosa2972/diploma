package by.bsuir.phoneshop.core.service;

import java.util.List;

import by.bsuir.phoneshop.core.models.Employee;
import by.bsuir.phoneshop.core.models.User;

public interface UserService
{
	User loadUserByUsername(final String username);

	List<User> getUsersExceptAdmin(int limit, int offset);

	void updateUserStatus(final int status, final String userName);

	void registerUser(final String username, final String password);

	List<Employee> getEmployees();
}
