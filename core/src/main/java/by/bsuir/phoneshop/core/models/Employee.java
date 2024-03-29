package by.bsuir.phoneshop.core.models;

import by.bsuir.phoneshop.core.models.enums.EmployeeType;

public class Employee
{
	private Long id;
	private String firstName;
	private String lastName;
	private String position;
	private EmployeeType employeeType;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getPosition()
	{
		return position;
	}

	public void setPosition(String position)
	{
		this.position = position;
	}

	public EmployeeType getEmployeeType()
	{
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType)
	{
		this.employeeType = employeeType;
	}
}
