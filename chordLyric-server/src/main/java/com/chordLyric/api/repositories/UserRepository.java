/**
 * 
 */
package com.chordLyric.api.repositories;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.chordLyric.api.models.impl.User;


/**
 * @author Yogen
 *
 */
@EnableScan
public interface UserRepository extends CrudRepository<User, String> {

}
