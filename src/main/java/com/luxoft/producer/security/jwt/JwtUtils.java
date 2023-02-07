package com.luxoft.producer.security.jwt;

import com.luxoft.producer.security.constants.RoleEnum;
import com.luxoft.producer.security.constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class JwtUtils {
	
	private static final long EXPIRE_DURATION = 1000L * 60L * 60L * 24L * 30L; // 30 days

	@Value("${security.jwt.key}")
	private String JWT_KEY;
	
	public String generateAccessToken(String username) {
		return Jwts.builder()
				.setClaims(Map.of(
						"username", username,
						"authorities", RoleEnum.ROLE.getRole()
				))
				.setSubject(username)
				.setIssuer("Luxoft")
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
				.signWith(Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8)))
				.compact();
	}
	
	public void validateAccessToken(HttpServletRequest request) {
		String jwt = request.getHeader(SecurityConstants.JWT_HEADER);
		if (jwt != null) {
			try {
				SecretKey key = Keys.hmacShaKeyFor(
						JWT_KEY.getBytes(StandardCharsets.UTF_8));

				Claims claims = Jwts.parserBuilder()
						.setSigningKey(key)
						.build()
						.parseClaimsJws(jwt)
						.getBody();
				String username = String.valueOf(claims.get("username"));
				String authorities = (String) claims.get("authorities");
				Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
						AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (Exception e) {
				throw new BadCredentialsException("Invalid Token received!");
			}
		}
	}

}
