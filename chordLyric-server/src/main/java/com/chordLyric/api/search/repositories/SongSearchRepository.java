/**
 * 
 */
package com.chordLyric.api.search.repositories;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.chordLyric.api.models.impl.Song;

/**
 * @author Yogen
 *
 */
public interface SongSearchRepository extends ElasticsearchRepository<Song, String> {
	List<Song> findByTitle(String title);
}
