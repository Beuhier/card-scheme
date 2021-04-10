package com.interview.nine.cardscheme.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;

import com.interview.nine.cardscheme.messages.ErrorMessage;
import com.interview.nine.cardscheme.messages.ErrorMessages;
import com.interview.nine.cardscheme.responses.InfoResponse;


@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = {CardInfoServiceException.class })
	public ResponseEntity<Object> userServiceExceptionHandler(CardInfoServiceException ex, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = {HttpStatusCodeException.class})
    public ResponseEntity<?> statusCodeException(HttpStatusCodeException ex, WebRequest request){
        InfoResponse response = new InfoResponse();
        response.setSuccess(false);
        response.setPayload(null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(value = {Exception.class })
	public ResponseEntity<Object> otherExceptionHandler(Exception ex, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = {InvalidInputException.class })
	public ResponseEntity<Object> InvalidInputException(InvalidInputException ex, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {NullPointerException.class })
	public ResponseEntity<Object> NullPointerException(NullPointerException ex, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ErrorMessages.NO_RECORD_FOUND.getErrorMessages());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
