package by.bsuir.phoneshop.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import by.bsuir.phoneshop.core.models.Employee;
import by.bsuir.phoneshop.core.models.User;
import by.bsuir.phoneshop.core.dao.UserDao;
import by.bsuir.phoneshop.core.service.UserService;


@Service
public class UserServiceImpl implements UserService
{
	@Resource
	private UserDao jdbcUserDao;

	@Value("${user.password.encryption.strength}")
	private int ENCRYPTION_STRENGTH;

	public User loadUserByUsername(final String username)
				 throws UsernameNotFoundException
	{
		return jdbcUserDao.getUserByUsername(username)
					 .orElseThrow(() -> new UsernameNotFoundException("User not present"));
	}

	@Override
	public List<User> getUsersExceptAdmin(final int limit, final int offset)
	{
		return jdbcUserDao.getUsersExceptAdmin(limit, offset);
	}

	@Override
	public void updateUserStatus(final int status, final String userName)
	{
		jdbcUserDao.updateUserStatus(status, userName);
	}

	@Override
	public void registerUser(final String username, final String password)
	{
		final User user = new User();
		user.setUserName(username);
		user.setIsAccountNonLocked(1);
		user.setRole("ROLE_CUSTOMER");

		final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(ENCRYPTION_STRENGTH);

		user.setPassword(bCryptPasswordEncoder.encode(password));


		jdbcUserDao.addNewUserToDb(user);
	}

	@Override
	public List<Employee> getEmployees()
	{
		return jdbcUserDao.getMainEmployees();
	}
}