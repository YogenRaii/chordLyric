package com.chordLyric.api.services;

import java.util.List;

import com.chordLyric.api.models.impl.User;


/**
 * @author Yogen
 *
 */
public interface UserService extends CommonService<String, User> {
	User findByEmailAndPassword(String email, String password);
	
	boolean exists(String userId);
}
