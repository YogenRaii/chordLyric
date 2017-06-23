package com.chordLyric.api.services;

import java.util.Optional;

import com.chordLyric.api.models.impl.Song;

/**
 * @author Yogen
 *
 */
public interface CommonService<T, R> {
	Optional<R> findOne(T t);
	
	String create(R r);
	
	void delete(String id);
	
	R update(R r);
}
