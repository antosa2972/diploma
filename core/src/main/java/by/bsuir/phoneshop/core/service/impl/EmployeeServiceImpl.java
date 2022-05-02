package by.bsuir.phoneshop.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import by.bsuir.phoneshop.core.beans.Employee;
import by.bsuir.phoneshop.core.dao.EmployeeDao;
import by.bsuir.phoneshop.core.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
	@Resource
	private EmployeeDao jdbcEmployeeDao;

	@Override
	public List<Employee> getEmployees()
	{
		return jdbcEmployeeDao.getMainEmployees();
	}
}
