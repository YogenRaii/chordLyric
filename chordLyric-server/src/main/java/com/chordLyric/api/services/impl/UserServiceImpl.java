package com.chordLyric.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.chordLyric.api.models.impl.Song;
import com.chordLyric.api.models.impl.User;
import com.chordLyric.api.repositories.UserRepository;
import com.chordLyric.api.services.UserService;

/**
 * @author Yogen
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Optional<User> findOne(String id) {
		return Optional.ofNullable(this.userRepository.findOne(id));
	}

	
	@Override
	public String create(User user) {
		return this.userRepository.save(user).getId();
	}

	
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public User update(User user) {
		return null;
	}
	

}
