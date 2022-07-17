package com.example.demo.Security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.Service.customerUserDetailsService;

@Configuration
@EnableWebSecurity
public class webSecurityConfigure extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Bean
	public UserDetailsService userDetailsService() {
		return new customerUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/register").permitAll()
				.antMatchers("/process_register").permitAll().antMatchers("/getId/**").permitAll().antMatchers("/demo")
				.hasAnyAuthority("User", "Admin").antMatchers("/demo/flight_details/edit_flight/**")
				.hasAnyAuthority("Admin").antMatchers("/demo/flight_details/delete_flight/**").hasAnyAuthority("Admin")
				.antMatchers("/demo/add_flight").hasAnyAuthority("Admin")
				.antMatchers("/demo/flight_details/add_schedule/**").hasAnyAuthority("Admin").anyRequest()
				.authenticated().and().formLogin().usernameParameter("username").passwordParameter("password").and()
				.logout().logoutSuccessUrl("/").permitAll().invalidateHttpSession(true).clearAuthentication(true).and()
				.exceptionHandling().accessDeniedPage("/403");

		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
}
