package com.chordLyric.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chordLyric.api.exceptions.ServiceException;
import com.chordLyric.api.models.common.ErrorCodes;
import com.chordLyric.api.models.impl.Song;
import com.chordLyric.api.models.references.ErrorReference;
import com.chordLyric.api.repositories.SongRepository;
import com.chordLyric.api.search.repositories.SongSearchRepository;
import com.chordLyric.api.services.SongService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Yogen
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class SongServiceImpl implements SongService {

	private final SongRepository songRepository;
	
	private final SongSearchRepository songSearchRepository;
	
	@Override
	public Optional<Song> findOne(String id) {
		return Optional.ofNullable(this.songRepository.findOne(id));
	}

	
	@Override
	public String create(Song song) { 
		Song createdSong = this.songRepository.save(song);
		return createdSong.getId();
	}


	@Override
	public void delete(String id) {
		try {
			this.songRepository.delete(id);
		} catch (Exception e) {
			log.error("Error while deleting song with ID {}: {}", id, e.getMessage());
			throw new ServiceException(ErrorCodes.EXC502.toString(), null);
		}
	}

	@Override
	public Song update(Song song) {
		return this.songRepository.save(song);
	}


	@Override
	public List<Song> findSongsByTitle(String title) {
		return this.songSearchRepository.findByTitle(title);
	}

}
