package by.bsuir.phoneshop.core.beans;

public class User
{
	private String userName;
	private String password;
	private int isAccountNonLocked;
	private String role;

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(final String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(final String password)
	{
		this.password = password;
	}

	public int getIsAccountNonLocked()
	{
		return isAccountNonLocked;
	}

	public void setIsAccountNonLocked(int isAccountNonLocked)
	{
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(final String role)
	{
		this.role = role;
	}
}
