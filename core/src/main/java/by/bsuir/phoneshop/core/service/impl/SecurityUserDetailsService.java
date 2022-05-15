package by.bsuir.phoneshop.core.service.impl;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import by.bsuir.phoneshop.core.beans.User;
import by.bsuir.phoneshop.core.dao.UserDao;


@Service
public class SecurityUserDetailsService
{
	@Resource
	private UserDao jdbcUserDao;

	public User loadUserByUsername(final String username)
				 throws UsernameNotFoundException
	{
		return jdbcUserDao.getUserByUsername(username)
					 .orElseThrow(() -> new UsernameNotFoundException("User not present"));
	}

}