/**
 * 
 */
package com.chordLyric.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chordLyric.api.models.impl.Song;

/**
 * @author Yogen
 *
 */
public interface SongRepository extends JpaRepository<Song, String> {

}
