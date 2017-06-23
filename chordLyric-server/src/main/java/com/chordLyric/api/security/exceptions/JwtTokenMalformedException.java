/**
 * 
 */
package com.chordLyric.api.security.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Yogen
 *
 */
public class JwtTokenMalformedException extends AuthenticationException {

	/**
	 * @param msg
	 */
	public JwtTokenMalformedException(String msg) {
		super(msg);
	}

}
