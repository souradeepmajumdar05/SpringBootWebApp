package com.first.springboot.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception 
	{
        auth.inMemoryAuthentication().withUser("admin")
        							 .password("1234")
                					 .roles("USER", "ADMIN");
    }
	/*formLogin()
		Takes care of the login page
		
		loginPage() – the custom login page
		loginProcessingUrl() – the url to submit the username and password to
		defaultSuccessUrl() – the landing page after a successful login
		failureUrl() – the landing page after an unsuccessful login
	 * */
	@Override
    protected void configure(HttpSecurity http) throws Exception 
	{
        http.authorizeRequests().antMatchers("/login")
        						.permitAll()
        						.antMatchers("/", "/*feature*/**")
        						.access("hasRole('USER')")
        						.and()
        						.formLogin();
    }
}
