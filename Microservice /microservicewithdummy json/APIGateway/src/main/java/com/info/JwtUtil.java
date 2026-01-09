package com.info;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
private final String SECRETKEY = "fahueigubadsknvlirblufbgkjasoifjoewafahka";

public Claims extractClaims(String token) {
	return Jwts.parserBuilder()
			.setSigningKey(Keys.hmacShaKeyFor(SECRETKEY.getBytes()))
			.build()
			.parseClaimsJws(token)
			.getBody();	
}
public void validateToken(String token) {
	extractClaims(token);
}
}
