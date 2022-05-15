package by.bsuir.phoneshop.core.dao.impl;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import by.bsuir.phoneshop.core.beans.User;
import by.bsuir.phoneshop.core.dao.UserDao;

@Service
public class JdbcUserDao implements UserDao
{
	@Resource
	private JdbcTemplate jdbcTemplate;

	private static final String SQL_GET_USER = "select * from users where userName=";

	@Override
	public Optional<User> getUserByUsername(final String username)
	{
		return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_GET_USER + "'" + username + "'", new BeanPropertyRowMapper<User>(User.class)));
	}
}
