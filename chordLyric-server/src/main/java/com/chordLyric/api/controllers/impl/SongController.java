package com.chordLyric.api.controllers.impl;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chordLyric.api.controllers.BaseController;
import com.chordLyric.api.exceptions.DataException;
import com.chordLyric.api.exceptions.NotFoundException;
import com.chordLyric.api.models.common.ErrorCodes;
import com.chordLyric.api.models.impl.Song;
import com.chordLyric.api.models.impl.UpdateDetail;
import com.chordLyric.api.models.impl.User;
import com.chordLyric.api.models.interfaces.impl.UserResponse;
import com.chordLyric.api.security.models.AuthenticatedUser;
import com.chordLyric.api.services.SongService;

import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Yogen
 *
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
@RestController
public class SongController implements BaseController<Song> {

	private final SongService songService;

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CONTRIBUTER')")
	@PostMapping(value = "/songs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> createSong(
			@ApiParam(value = "Request Body for Song", required = true) @Valid @RequestBody Song song,
			BindingResult bindingResult,
			@ApiParam(value = "Token with format 'Bearer Token'", required = true) @RequestHeader("Authorization") final String authorization) {
		if (bindingResult.hasErrors()) {
			throw new DataException(ErrorCodes.EXC400.toString(), bindingResult);
		}
		
		song.setApproved(false);
		
		//setting contributer ID with currently loggedIn user
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication auth = context.getAuthentication();
		song.setContributerId(((AuthenticatedUser)auth.getPrincipal()).getId());
		
		String createdSongId = this.songService.create(song);
		log.info("Song saved id: {}", createdSongId);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CONTRIBUTER')")
	@PutMapping(value = "/songs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Song> updateSong(
			@ApiParam(value = "Request Body for Song", required = true) @Valid @RequestBody Song song,
			BindingResult bindingResult,
			@ApiParam(value = "Token with format 'Bearer Token'", required = true) @RequestHeader("Authorization") final String authorization) {
		if (bindingResult.hasErrors()) {
			throw new DataException(ErrorCodes.EXC400.toString(), bindingResult);
		}
		
		song.setApproved(false);
		
		//setting updater contributer ID with currently loggedIn user
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication auth = context.getAuthentication();
		
		AuthenticatedUser contributer = (AuthenticatedUser)auth.getPrincipal();
		
		UpdateDetail lastUpdate = new UpdateDetail(new Date(), contributer.getId(), contributer.getUsername());
		
		song.setLastUpdate(lastUpdate);
		
		Song updatedSong = this.songService.update(song);
		log.info("Song updated id: {}", updatedSong.getId());

		return new ResponseEntity<>(updatedSong, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/songs/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> deleteSong(
			@ApiParam(value = "Id for Song", required = true) @PathVariable("id") String id,
			@ApiParam(value = "Token with format 'Bearer Token'", required = true) @RequestHeader("Authorization") final String authorization) {
		this.songService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/songSearch", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Song>> getSongs(
			@ApiParam(value = "Id for Song", required = true) @PathVariable("title") String title) {

		List<Song> songs = this.songService.findSongsByTitle(title);
		return new ResponseEntity<List<Song>>(songs, HttpStatus.OK);
	}
		

}
