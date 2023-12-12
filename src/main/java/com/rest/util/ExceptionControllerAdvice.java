package com.rest.util;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rest.exception.CustomRestaurantException;

//import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	@Autowired
//	private Environment environment;
	
	@ExceptionHandler(CustomRestaurantException.class)
	public ResponseEntity<ErrorInfo>infyPandaExceptionHandler(CustomRestaurantException exception) {
		logger.error(exception.getMessage());
		logger.error(exception.toString());
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorMessage(exception.getMessage());
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception) {
		logger.error(exception.getMessage());
		logger.error(exception.toString());
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorInfo.setErrorMessage(exception.getMessage());

		return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({MethodArgumentNotValidException.class,jakarta.validation.ConstraintViolationException.class})
	//handler for methodargumentnotvalidexception and ConstraintsViolationException
	public ResponseEntity<ErrorInfo> exceptionHandler(Exception ex) {

		logger.error(ex.getMessage(), ex);

		String errorMessage;

		if (ex instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException mex = (MethodArgumentNotValidException) ex;
			errorMessage = mex.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage())
					.collect(Collectors.joining(", "));
		} else {
			jakarta.validation.ConstraintViolationException cex = (jakarta.validation.ConstraintViolationException) ex;
			errorMessage = cex.getConstraintViolations().stream().map(x -> x.getMessage())
					.collect(Collectors.joining(", "));
		}
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorMessage(errorMessage);

		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}
}
