package by.bsuir.phoneshop.core.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import by.bsuir.phoneshop.core.beans.Employee;
import by.bsuir.phoneshop.core.dao.EmployeeDao;
import by.bsuir.phoneshop.core.dao.extractors.EmployeeListResultSetExtractor;

@Component
public class JdbcEmployeeDao implements EmployeeDao
{
	private static final String SQL_GET_EMPLOYEES = "select * from employees";

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Resource
	private EmployeeListResultSetExtractor employeeListResultSetExtractor;

	@Override
	public List<Employee> getMainEmployees()
	{
		return jdbcTemplate.query(SQL_GET_EMPLOYEES, employeeListResultSetExtractor);
	}
}
