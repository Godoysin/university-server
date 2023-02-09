package com.luxoft.producer.security.jwt;

import com.luxoft.producer.security.constants.RoleEnum;
import com.luxoft.producer.security.constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.util.ReflectionTestUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JwtUtilsTest {

    @InjectMocks
    private JwtUtils jwtUtilsMock;

    @Mock
    private JwtDateConfigurator jwtDateConfiguratorMock;

    @Mock
    private MockHttpServletRequest mockHttpServletRequest;

    private static final String JWT_KEY = "FrDW7TEAmfTfg9mwnZ5swSt51VKAddKo";
    private String username;

    @BeforeEach
    protected void setKeyBeforeTest() {
        username = "test";
        ReflectionTestUtils.setField(jwtUtilsMock, "JWT_KEY", JWT_KEY);
    }

    // given

    // when

    // then

    @Test
    public void shouldGenerateJwtAndVerifyFields() {
        // given
        when(jwtDateConfiguratorMock.generateDate()).thenReturn(new Date());
        when(jwtDateConfiguratorMock.getExpireDuration()).thenReturn((new JwtDateConfigurator()).getExpireDuration());

        // when
        String jwt = jwtUtilsMock.generateAccessToken(username);

        // then
        verify(jwtDateConfiguratorMock).generateDate();
        verify(jwtDateConfiguratorMock).getExpireDuration();

        SecretKey key = Keys.hmacShaKeyFor(
                JWT_KEY.getBytes(StandardCharsets.UTF_8));

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        assertEquals(username, String.valueOf(claims.get(SecurityConstants.JWT_PAYLOAD_USERNAME)));
        assertEquals(RoleEnum.ROLE.getRole(), String.valueOf(claims.get(SecurityConstants.JWT_PAYLOAD_AUTHORITIES)));
        assertEquals(SecurityConstants.JWT_ISSUER, String.valueOf(claims.get("iss")));

        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        LocalDateTime generateLocalDateTimeIat = DateComparator.convertToLocalDateTimeViaInstant(new Date(Long.parseLong(String.valueOf(claims.get("iat"))) * 1000));

        assertEquals(currentLocalDateTime.getYear(), generateLocalDateTimeIat.getYear());
        assertEquals(currentLocalDateTime.getDayOfYear(), generateLocalDateTimeIat.getDayOfYear());
        assertEquals(currentLocalDateTime.getHour(), generateLocalDateTimeIat.getHour());
        assertEquals(currentLocalDateTime.getMinute(), generateLocalDateTimeIat.getMinute());

        LocalDateTime generateLocalDateTimeExp = DateComparator.convertToLocalDateTimeViaInstant(new Date(Long.parseLong(String.valueOf(claims.get("exp"))) * 1000));
        assertEquals(currentLocalDateTime.getYear(), generateLocalDateTimeExp.getYear());
        assertEquals(currentLocalDateTime.getDayOfYear() + 30, generateLocalDateTimeExp.getDayOfYear());
        assertEquals(currentLocalDateTime.getHour(), generateLocalDateTimeExp.getHour());
        assertEquals(currentLocalDateTime.getMinute(), generateLocalDateTimeExp.getMinute());
    }

    @Test
    public void shouldNotRegisterUserInSecurityContextAndNotGenerateException() {
        // given
        when(mockHttpServletRequest.getHeader(SecurityConstants.JWT_HEADER)).thenReturn(null);

        // when
        jwtUtilsMock.validateAccessToken(mockHttpServletRequest);

        // then
        verify(mockHttpServletRequest).getHeader(SecurityConstants.JWT_HEADER);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        assertNull(securityContext.getAuthentication());
    }

    @Test
    public void shouldRegisterUserInSecurityContextAndVerifyFields() {
        // given
        when(jwtDateConfiguratorMock.generateDate()).thenReturn(new Date());
        when(jwtDateConfiguratorMock.getExpireDuration()).thenReturn((new JwtDateConfigurator()).getExpireDuration());

        String jwt = jwtUtilsMock.generateAccessToken(username);

        verify(jwtDateConfiguratorMock).generateDate();
        verify(jwtDateConfiguratorMock).getExpireDuration();

        when(mockHttpServletRequest.getHeader(SecurityConstants.JWT_HEADER)).thenReturn(jwt);

        // when
        jwtUtilsMock.validateAccessToken(mockHttpServletRequest);

        // then
        verify(mockHttpServletRequest).getHeader(SecurityConstants.JWT_HEADER);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        assertEquals(username, securityContext.getAuthentication().getName());
        assertEquals(1, securityContext.getAuthentication().getAuthorities().size());
        assertNull(securityContext.getAuthentication().getCredentials());
        assertNull(securityContext.getAuthentication().getDetails());
        assertEquals(username, securityContext.getAuthentication().getPrincipal());
    }

    @Test
    public void shouldGenerateAndExpiredTokenAndGenerateBadCredentialsExceptionWhenValidatingIt() {
        // given
        when(jwtDateConfiguratorMock.generateDate()).thenReturn(new Date());

        // getExpireDuration() is configured to return a long representing a 30 days long.
        long expiredTime = -(new JwtDateConfigurator()).getExpireDuration() / 30L;
        when(jwtDateConfiguratorMock.getExpireDuration()).thenReturn(expiredTime);

        String jwt = jwtUtilsMock.generateAccessToken(username);

        verify(jwtDateConfiguratorMock).generateDate();
        verify(jwtDateConfiguratorMock).getExpireDuration();

        when(mockHttpServletRequest.getHeader(SecurityConstants.JWT_HEADER)).thenReturn(jwt);

        // when
        Executable executable = () -> jwtUtilsMock.validateAccessToken(mockHttpServletRequest);

        // then
        assertThrows(BadCredentialsException.class, executable);

        verify(mockHttpServletRequest).getHeader(SecurityConstants.JWT_HEADER);
    }

}
