package com.chordLyric.api.models.references;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix="errors")
@Setter
@Getter
public class ErrorReference {
	private Map<String, String> description = new HashMap<>();
	
	public String getDescription(String errorCode) {
		return this.description.get(errorCode);
	}
}
