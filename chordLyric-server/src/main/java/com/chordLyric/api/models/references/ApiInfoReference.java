package com.chordLyric.api.models.references;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix="api")
@Setter
@Getter
public class ApiInfoReference {
	private Map<String, String> info = new HashMap<>();
	
	public String getInfo(String key) {
		return this.info.get(key);
	}
}
