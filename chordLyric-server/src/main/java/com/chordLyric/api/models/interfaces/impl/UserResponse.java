package com.chordLyric.api.models.interfaces.impl;

import org.springframework.hateoas.ResourceSupport;

import com.chordLyric.api.models.impl.User;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UserResponse extends ResourceSupport {

	private User user;
}
