package com.chordLyric.api.services;

import java.util.List;

import com.chordLyric.api.models.impl.User;


/**
 * @author Yogen
 *
 */
public interface UserService extends CommonService<String, User> {
	User createUser(User user);
}
