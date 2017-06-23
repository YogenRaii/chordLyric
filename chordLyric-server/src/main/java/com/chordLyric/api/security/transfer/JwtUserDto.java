package com.chordLyric.api.security.transfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Holds info extracted from JWT
 * 
 * @author Yogen
 *
 */
@Getter
@AllArgsConstructor
@ToString
public class JwtUserDto {
	private String id;

    private String username;

    private String role;

}
