/**
 * 
 */
package com.chordLyric.api.repositories;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.chordLyric.api.models.impl.Song;

/**
 * @author Yogen
 *
 */
@EnableScan
public interface SongRepository extends CrudRepository<Song, String> {

}
