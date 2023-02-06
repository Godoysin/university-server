package com.luxoft.producer.security;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class RequestExceptionHandler implements AuthenticationEntryPoint, Serializable {

	@Serial
	private static final long serialVersionUID = -7858869558953243875L;

	@Override
	public void commence(final HttpServletRequest request, HttpServletResponse response, final AuthenticationException authException) throws IOException {
		setResponseError(response, HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
	}

	// 400
	@ExceptionHandler(value = {BadRequestException.class, MissingServletRequestParameterException.class})
	public void commence(final HttpServletRequest request, HttpServletResponse response, @NotNull final BadRequestException exception) {
		setResponseError(response, exception.getResponse().getStatus());
	}


	// 401
	@ExceptionHandler(value = {BadCredentialsException.class})
	public void commence(final HttpServletRequest request, HttpServletResponse response, @NotNull final BadCredentialsException exception) {
		setResponseError(response, HttpStatus.UNAUTHORIZED.value());
	}

	//500
	@ExceptionHandler(value = {Exception.class})
	public void commence(final HttpServletRequest request, HttpServletResponse response, final Exception exception) throws IOException {
		setResponseError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, String.format("Internal Server Error: %s", exception.getMessage()));
	}

	private void setResponseError(HttpServletResponse response, int errorCode, String errorMessage) throws IOException {
		response.setStatus(errorCode);
		response.getWriter().write(errorMessage);
		response.getWriter().flush();
		response.getWriter().close();
	}

	private void setResponseError(HttpServletResponse response, int errorCode) {
		response.setStatus(errorCode);
	}
}
