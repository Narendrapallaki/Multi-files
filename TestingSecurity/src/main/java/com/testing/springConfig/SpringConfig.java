package com.testing.springConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SpringConfig {
	
	@Autowired
	private EntryPoint entryPoint;
	
	
	@Bean
	public SecurityFilterChain chain(HttpSecurity http) throws Exception
	{
		
		http.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> auth.requestMatchers("/year/month").permitAll())
		.exceptionHandling(ex->ex.authenticationEntryPoint(entryPoint));
		
		return http.build();
		
	}

}
