package by.bsuir.phoneshop.core.service.impl;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import by.bsuir.phoneshop.core.models.User;
import by.bsuir.phoneshop.core.service.UserService;

@Component
public class AuthProvider implements AuthenticationProvider
{
	@Resource
	private UserService userDetailsService;

	@Value("${user.password.encryption.strength}")
	private int ENCRYPTION_STRENGTH;

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException
	{
		final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(ENCRYPTION_STRENGTH);

		final String username = authentication.getName();
		User user;
		try
		{
			user = userDetailsService.loadUserByUsername(username);
		}
		catch (final Exception e)
		{
			throw new BadCredentialsException("Username or password is invalid");
		}

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
