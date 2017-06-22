package com.chordLyric.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Base interface for all controllers intercepting http requests.
 * 
 * @author Yogen
 *
 */
@RequestMapping("/api/v0.1")
public interface BaseController <T> {
	//intentionally left blank
}
