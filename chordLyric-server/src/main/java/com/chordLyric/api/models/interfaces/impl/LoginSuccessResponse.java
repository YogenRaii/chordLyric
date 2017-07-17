package com.chordLyric.api.models.interfaces.impl;

import org.springframework.hateoas.ResourceSupport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class LoginSuccessResponse extends ResourceSupport {

	private String userId;
	
	private String token;
}
