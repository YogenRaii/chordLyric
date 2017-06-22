package com.chordLyric.api.exceptions;

import lombok.Getter;
import com.chordLyric.api.models.common.Error;

@Getter
public class NotFoundException extends BaseException {
	/**
	 * Serial UUID
	 */
	private static final long serialVersionUID = -4240564240662292632L;
	
	private final Error error;
	
	public NotFoundException(String message, Error errors) {
		super(message);
		this.error = errors;
	}
}
