package com.chordLyric.api.exceptions;

import com.chordLyric.api.models.common.Error;
import lombok.Getter;

@Getter
public class ServiceException extends BaseException {
	/**
	 * Serial UUID
	 */
	private static final long serialVersionUID = -4240564240662292632L;
	
	private final Error error;
	
	public ServiceException(String message, Error errors) {
		super(message);
		this.error = errors;
	}
}
