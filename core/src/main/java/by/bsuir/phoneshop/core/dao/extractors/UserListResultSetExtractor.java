package by.bsuir.phoneshop.core.dao.extractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import by.bsuir.phoneshop.core.models.User;

@Component
public class UserListResultSetExtractor implements ResultSetExtractor<List<User>>
{
	@Override
	public List<User> extractData(final ResultSet resultSet) throws SQLException, DataAccessException
	{
		final List<User> users = new ArrayList<>();
		while (resultSet.next())
		{
			final User user = new User();

			user.setUserName(resultSet.getString("userName"));
			user.setPassword(resultSet.getString("password"));
			user.setRole(resultSet.getString("role"));
			user.setIsAccountNonLocked(resultSet.getInt("isAccountNonLocked"));

			users.add(user);
		}
		return users;
	}
}
