package com.chordLyric.api.exceptions;

import org.springframework.validation.Errors;

import lombok.Getter;

@Getter
public class DataException extends BaseException {
	/**
	 * Serial UUID
	 */
	private static final long serialVersionUID = 2502363624937013988L;

	private final Errors errors;
	
	public DataException(String message, Errors errors) {
		super(message);
		this.errors = errors;
	}

}
