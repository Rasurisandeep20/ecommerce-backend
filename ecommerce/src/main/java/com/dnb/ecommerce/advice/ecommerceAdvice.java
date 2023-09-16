package com.dnb.ecommerce.advice;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dnb.ecommerce.exceptions.IdNotFoundException;

@ControllerAdvice
public class ecommerceAdvice {
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "invalid id provided")
	@ExceptionHandler(IdNotFoundException.class)
	public void invalidAccoundIdExceptionHandler(IdNotFoundException e) {
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Map<String, String>> handleException(HttpRequestMethodNotSupportedException e)
			throws IOException {
		String provided = e.getMethod();
		List<String> supported = List.of(e.getSupportedMethods());
		String error = provided + "is not of the supported Http Methods(" + String.join(",", supported) + ")";
		Map<String, String> errorResponse = Map.of("error", error, "message", e.getLocalizedMessage(), "status",
				HttpStatus.METHOD_NOT_ALLOWED.toString());
		return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
	}
}
