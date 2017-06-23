package com.chordLyric.api.models.interfaces.impl;

import java.util.List;

import com.chordLyric.api.models.common.Error;
import com.chordLyric.api.models.interfaces.BaseResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse implements BaseResponse {
	private int code;
	private String developersMessage;
	private List<Error> errors;
}
