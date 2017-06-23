package com.chordLyric.api.security.exceptions.handlers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.chordLyric.api.exceptions.BaseException;
import com.chordLyric.api.exceptions.DataException;
import com.chordLyric.api.exceptions.NotFoundException;
import com.chordLyric.api.exceptions.ServiceException;
import com.chordLyric.api.models.interfaces.impl.ErrorResponse;
import com.chordLyric.api.models.references.ErrorReference;
import com.chordLyric.api.models.common.Error;

import lombok.RequiredArgsConstructor;

/**
 * @author Yogen
 *
 */
@ControllerAdvice
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	private final MessageSourceAccessor messageSourceAccessor;

	private final ErrorReference errorReference;
	
	@ExceptionHandler(DataException.class)
	public ResponseEntity<ErrorResponse> handleDataException(DataException exception) {
		final List<Error> errors = new ArrayList<>();
		final List<FieldError> fieldErrors = exception.getErrors().getFieldErrors();
		for(FieldError fieldError: fieldErrors) {
			final Error error = new Error(fieldError.getCode(), fieldError.getField(), messageSourceAccessor.getMessage(fieldError));
			errors.add(error);
		}
		return new ResponseEntity<ErrorResponse>(generateErrorResponse(HttpStatus.BAD_REQUEST.value(), exception, errors), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException exception) {
		final List<Error> errors = new ArrayList<>();
		final Error error = exception.getError();
		if(error != null)
			errors.add(error);
		return new ResponseEntity<ErrorResponse>(generateErrorResponse(HttpStatus.NOT_FOUND.value(), exception, errors), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ErrorResponse> handleServiceException(ServiceException exception) {
		final List<Error> errors = new ArrayList<>();
		final Error error = exception.getError();
		if(error != null)
			errors.add(error);
		return new ResponseEntity<ErrorResponse>(generateErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception, errors), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ErrorResponse generateErrorResponse(int code, BaseException exception, List<Error> errors) {
		final ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setCode(code);
		errorResponse.setDevelopersMessage(errorReference.getDescription(exception.getMessage()));
		errorResponse.setErrors(errors);
		return errorResponse;
	}
}
