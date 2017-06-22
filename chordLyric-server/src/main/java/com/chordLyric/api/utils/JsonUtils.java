package com.chordLyric.api.utils;

import com.chordLyric.api.exceptions.ServiceException;
import com.chordLyric.api.models.common.ErrorCodes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Yogen
 *
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtils {
	private static final ObjectMapper mapper = new ObjectMapper();
	
	public static String getJsonString(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			log.error("Error occured while writing JSON: ", e);
			throw new ServiceException(ErrorCodes.EXC501.toString(), null);
		}
	}

}
