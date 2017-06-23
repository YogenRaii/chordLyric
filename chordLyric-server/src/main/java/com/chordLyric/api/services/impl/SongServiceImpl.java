/**
 * 
 */
package com.chordLyric.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chordLyric.api.models.impl.Song;
import com.chordLyric.api.repositories.SongRepository;
import com.chordLyric.api.services.SongService;

import lombok.RequiredArgsConstructor;

/**
 * @author Yogen
 *
 */
@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class SongServiceImpl implements SongService {

	private final SongRepository songRepository;
	
	@Override
	public Optional<Song> findOne(String id) {
		return Optional.of(this.songRepository.findOne(id));
	}

	
	@Override
	public String createSong(Song song) {
		Song createdSong = this.songRepository.save(song);
		return createdSong.getId();
	}

}
