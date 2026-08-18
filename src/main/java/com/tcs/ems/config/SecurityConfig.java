package com.tcs.ems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) {
		http.
		csrf(csrf->csrf.disable()).
		authorizeHttpRequests(auth->auth.
				requestMatchers("/users/register","/users/verify-otp").permitAll().
//				requestMatchers().permitAll().
				requestMatchers(HttpMethod.GET,"/employees/**").hasAnyRole("ADMIN","USER").
				requestMatchers("/employees/**").hasRole("ADMIN").
				anyRequest().authenticated()).
		httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	
	@Bean
	UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		UserDetails admin=User.
				withUsername("admin").
				password(passwordEncoder.encode("admin123")).
				roles("ADMIN").
				build();
		
		UserDetails user=User.withUsername("user").
				password(passwordEncoder.encode("user123")).
				roles("USER").
				build();
		
		return new InMemoryUserDetailsManager(admin,user);
	}
}
