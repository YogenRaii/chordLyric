package com.chordLyric.api.models.interfaces.impl;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.chordLyric.api.models.interfaces.BaseRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Yogen
 *
 */
@Getter
@Setter
@ToString
public class LoginRequest implements BaseRequest {

	@NotEmpty 
	@NotNull
	private String email;

	@NotEmpty 
	@NotNull
	private String password;
	
}
