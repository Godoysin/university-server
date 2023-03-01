package com.project.producer.security.jwt;

import com.project.producer.security.constants.RoleEnum;
import com.project.producer.security.constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Value("${security.jwt.key}")
	private String jwtKey;

	private final JwtDateConfigurator jwtDateConfigurator;

	@Autowired
	public JwtUtils(JwtDateConfigurator jwtDateConfigurator) {
		this.jwtDateConfigurator = jwtDateConfigurator;
	}

	public String generateAccessToken(String username) {
		return Jwts.builder()
				.setClaims(Map.of(
						SecurityConstants.JWT_PAYLOAD_AUTHORITIES, RoleEnum.ROLE.getRole()
				))
				.setSubject(username)
				.setIssuer(SecurityConstants.JWT_ISSUER)
				.setIssuedAt(jwtDateConfigurator.generateDate())
				.setExpiration(new Date(System.currentTimeMillis() + jwtDateConfigurator.getExpireDuration()))
				.signWith(Keys.hmacShaKeyFor(jwtKey.getBytes(StandardCharsets.UTF_8)))
				.compact();
	}
	
	public void validateAccessToken(HttpServletRequest request) {
		String jwt = request.getHeader(SecurityConstants.JWT_HEADER);
		if (jwt != null) {
			try {
				SecretKey key = Keys.hmacShaKeyFor(
						jwtKey.getBytes(StandardCharsets.UTF_8));

				Claims claims = Jwts.parserBuilder()
						.setSigningKey(key)
						.build()
						.parseClaimsJws(jwt)
						.getBody();
				String username = String.valueOf(claims.get(SecurityConstants.JWT_PAYLOAD_USERNAME));
				String authorities = String.valueOf(claims.get(SecurityConstants.JWT_PAYLOAD_AUTHORITIES));
				Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
						AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (Exception e) {
				log.error("{}", e.toString());
				throw new BadCredentialsException("Invalid Token received!");
			}
		}
	}

}
