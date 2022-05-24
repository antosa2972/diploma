package by.bsuir.phoneshop.core.dao.extractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import by.bsuir.phoneshop.core.models.Employee;
import by.bsuir.phoneshop.core.models.enums.EmployeeType;

@Component
public class EmployeeListResultSetExtractor implements ResultSetExtractor<List<Employee>>
{
	@Override
	public List<Employee> extractData(final ResultSet resultSet) throws SQLException, DataAccessException
	{
		final List<Employee> employees = new ArrayList<>();
		while (resultSet.next())
		{
			final Employee employee = new Employee();
			employee.setId(resultSet.getLong("id"));
			employee.setFirstName(resultSet.getString("firstName"));
			employee.setLastName(resultSet.getString("lastName"));
			employee.setPosition(resultSet.getString("position"));
			employee.setEmployeeType(EmployeeType.valueOf(resultSet.getString("employeeType")));

			employees.add(employee);
		}
		return employees;
	}
}
