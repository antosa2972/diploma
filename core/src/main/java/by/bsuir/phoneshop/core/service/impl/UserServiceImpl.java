package by.bsuir.phoneshop.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import by.bsuir.phoneshop.core.beans.User;
import by.bsuir.phoneshop.core.dao.UserDao;
import by.bsuir.phoneshop.core.service.UserService;


@Service
public class UserServiceImpl implements UserService
{
	@Resource
	private UserDao jdbcUserDao;


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
		final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

		final User user = new User();
		user.setUserName(username);
		user.setPassword(bCryptPasswordEncoder.encode(password));
		user.setIsAccountNonLocked(1);
		user.setRole("ROLE_CUSTOMER");

		jdbcUserDao.addNewUserToDb(user);
	}
}