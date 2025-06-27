package com.crm.customer.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.crm.customer.filter.JwtAuthenticationFilter;
//
//@Configuration
//@EnableMethodSecurity
//public class SecurityConfig {
//	
//	private final JwtAuthenticationFilter jwtFilter;
//
//	public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
//		this.jwtFilter = jwtFilter;
//	}
//
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.csrf(csrf -> csrf.disable())
//				.authorizeHttpRequests(
//						auth -> auth.requestMatchers("/auth/**").permitAll().anyRequest().authenticated())
//				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//		return http.build();
//	}
//}


import com.crm.customer.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;
 
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
 
    private final JwtAuthenticationFilter jwtFilter;
 
    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }
 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors() // Enable CORS
            .and()
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
 
        return http.build();
    }
}