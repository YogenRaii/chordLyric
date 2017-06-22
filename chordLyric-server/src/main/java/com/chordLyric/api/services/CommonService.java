package com.chordLyric.api.services;

import java.util.Optional;

/**
 * @author Yogen
 *
 */
public interface CommonService<T, R> {
	Optional<R> findOne(T t);
}
