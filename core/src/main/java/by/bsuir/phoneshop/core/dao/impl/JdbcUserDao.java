package by.bsuir.phoneshop.core.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import by.bsuir.phoneshop.core.beans.User;
import by.bsuir.phoneshop.core.dao.UserDao;
import by.bsuir.phoneshop.core.dao.extractors.UserListResultSetExtractor;

@Service
public class JdbcUserDao implements UserDao
{
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Resource
	private UserListResultSetExtractor resultSetExtractor;

	private static final String SQL_GET_USER = "select * from users where userName=";

	private static final String SQL_GET_USERS_EXCEPT_ADMIN = "select * from users where userName not like 'admin'";

	private static final String SQL_UPDATE_USER_STATUS = "update users set isAccountNonLocked= ? where userName = ?";

	private static final String SQL_INSERT_NEW_USER = "insert into users(userName,password,isAccountNonLocked,role) values (?,?,?,?)";

	@Override
	public Optional<User> getUserByUsername(final String username)
	{
		return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_GET_USER + "'" + username + "'", new BeanPropertyRowMapper<User>(User.class)));
	}

	@Override
	public List<User> getUsersExceptAdmin(int limit, int offset)
	{
		return jdbcTemplate.query(SQL_GET_USERS_EXCEPT_ADMIN + " limit " + limit + " offset " + offset, resultSetExtractor);
	}

	@Override
	public void updateUserStatus(int status, String username)
	{
		jdbcTemplate.update(SQL_UPDATE_USER_STATUS, status, username);
	}

	@Override
	public void addNewUserToDb(final User user)
	{
		jdbcTemplate.update(SQL_INSERT_NEW_USER, user.getUserName(), user.getPassword(), user.getIsAccountNonLocked(), user.getRole());
	}
}
