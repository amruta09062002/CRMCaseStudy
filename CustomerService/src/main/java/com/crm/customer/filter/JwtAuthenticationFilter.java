package com.crm.customer.filter;

import jakarta.servlet.FilterChain;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.crm.customer.jwt.JwtUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);
			try {
				String username = jwtUtil.extractUsername(token);
				String role = jwtUtil.extractRole(token);
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					List<SimpleGrantedAuthority> authorities = Collections
							.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							username, null, authorities);
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
					System.out.println("JWT Authenticated: " + username + " with role " + authorities);
				}
			} catch (Exception ex) {
				System.out.println("JWT error: " + ex.getMessage());
			}
		}
		filterChain.doFilter(request, response);
	}
}