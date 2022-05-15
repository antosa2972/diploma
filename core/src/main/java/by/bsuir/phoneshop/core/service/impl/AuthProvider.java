package by.bsuir.phoneshop.core.service.impl;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import by.bsuir.phoneshop.core.beans.User;
import by.bsuir.phoneshop.core.service.UserService;

@Component
public class AuthProvider implements AuthenticationProvider
{
	@Resource
	private UserService userDetailsService;

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException
	{
		final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

		final String username = authentication.getName();

		final User user = userDetailsService.loadUserByUsername(username);

		if (user.getIsAccountNonLocked() == 0)
		{
			throw new LockedException("You are blocked!");
		}
		else
		{
			if (passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword()))
			{
				return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials()
							 .toString(), Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
			}
			else throw new BadCredentialsException("Username or password is invalid");
		}

	}

	@Override
	public boolean supports(final Class<?> aClass)
	{
		return aClass.equals(UsernamePasswordAuthenticationToken.class);
	}
}
