package com.crm.authentication.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
 
@Component
public class JwtUtil {
 
	private final String secretKey = "mysecretkey12345678901234567890123456789012"; // must be at least 256 bits
	private final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
 
	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(java.util.Base64.getEncoder().encodeToString(secretKey.getBytes()));
		return Keys.hmacShaKeyFor(keyBytes);
//   return Keys.hmacShaKeyFor(secretKey.getBytes());
 
	}
 
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
 
	public String extractRole(String token) {
		return extractAllClaims(token).get("role", String.class);
	}
 
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
 
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
	}
 
	public String generateToken(String username, String role) {
		Map<String, Object> claims = Map.of("role", role);
 
		return Jwts.builder() // header
				.setClaims(claims) // payload
				.setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
				.signWith(getSignInKey(), SignatureAlgorithm.HS256) // signature
				.compact();
	}
 
//public boolean isTokenValid(String token, String username) {
//   final String extractedUsername = extractUsername(token);
//   return (extractedUsername.equals(username) && !isTokenExpired(token));
//}
//
//private boolean isTokenExpired(String token) {
//   return extractAllClaims(token).getExpiration().before(new Date());
//}
 
}