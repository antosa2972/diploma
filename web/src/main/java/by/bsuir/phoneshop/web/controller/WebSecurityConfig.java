package by.bsuir.phoneshop.web.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import by.bsuir.phoneshop.core.service.impl.AuthProvider;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Resource
	AuthProvider authProvider;

	@Override
	protected void configure(final HttpSecurity http) throws Exception
	{
		http.csrf().disable()
					 .authorizeRequests()
					 .antMatchers("/admin/**").hasRole("ADMIN")
					 .and()
					 .authorizeRequests()
					 .antMatchers("/ajax-cart", "/order","quick-order","/cart").hasRole("CUSTOMER")
					 .and()
					 .formLogin()
					 .loginPage("/login")
					 .defaultSuccessUrl("/product-list")
					 .permitAll()
					 .and()
					 .logout()
					 .logoutUrl("/logout")
					 .logoutSuccessUrl("/product-list")
					 .permitAll();
	}

	@Autowired
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception
	{
		auth.authenticationProvider(authProvider);
	}
}
