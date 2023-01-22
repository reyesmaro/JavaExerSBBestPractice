package com.maro.crud.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("user").password("userpass").roles("READONLY")
//		.and().withUser("admin").password("adminpass").roles("ADMIN")
//		.and().withUser("user2").password("user2pass").roles("UPDATEDELETE");
//	}
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Autowired
	private DataSource dataSource;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select username, password, enabled from users where username=?")
		.authoritiesByUsernameQuery("select username, role from users where username=?");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/getAllCities").hasAnyRole("ADMIN" , "UPDATEDELETE" , "READONLY").and().httpBasic();
		http.authorizeRequests().antMatchers("/addCity").hasAnyRole("ADMIN" , "UPDATEDELETE").and().httpBasic();
		http.authorizeRequests().antMatchers("/updateCity").hasAnyRole("ADMIN" , "UPDATEDELETE").and().httpBasic();
		http.authorizeRequests().antMatchers("/deleteCity").hasAnyRole("ADMIN" , "UPDATEDELETE").and().httpBasic();
	}
}
