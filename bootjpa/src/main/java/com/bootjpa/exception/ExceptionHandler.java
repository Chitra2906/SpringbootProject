package com.bootjpa.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	private final Logger logger =LoggerFactory.getLogger(this.getClass());
	@org.springframework.web.bind.annotation.ExceptionHandler({NullPointerException.class , EmptyResultDataAccessException.class})
	public final ResponseEntity<Object> NoSuchUserExistsException(Exception ex, WebRequest request){
		//	Put data in the ExceptionResponseObject
		ExceptionResponse exceptionresponse = new ExceptionResponse(new Date(),"No such user exists", request.getDescription(false));
		logger.error(ex.toString());
		return new ResponseEntity(exceptionresponse, HttpStatus.NOT_FOUND);

	}

	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
		ExceptionResponse exceptionresponse = new ExceptionResponse(new Date(),"Exception occurred", request.getDescription(false));
		logger.error(ex.toString());
		return new ResponseEntity(exceptionresponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
