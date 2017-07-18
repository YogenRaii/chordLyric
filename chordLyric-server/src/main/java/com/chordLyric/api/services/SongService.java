/**
 * 
 */
package com.chordLyric.api.services;

import java.util.List;

import com.chordLyric.api.models.impl.Song;

/**
 * @author Yogen
 *
 */
public interface SongService extends CommonService<String, Song> {

	List<Song> findSongsByTitle(String title);
}
