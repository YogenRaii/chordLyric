/**
 * 
 */
package com.chordLyric.api.repositories;

import org.springframework.data.repository.CrudRepository;

import com.chordLyric.api.models.impl.User;


/**
 * @author Yogen
 *
 */
public interface UserRepository extends CrudRepository<User, String> {

}
