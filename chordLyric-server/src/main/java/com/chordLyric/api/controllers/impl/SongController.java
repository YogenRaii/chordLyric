package com.chordLyric.api.controllers.impl;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Date;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.chordLyric.api.controllers.BaseController;
import com.chordLyric.api.exceptions.DataException;
import com.chordLyric.api.models.common.ErrorCodes;
import com.chordLyric.api.models.impl.Song;
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
		
		String createdSongId = this.songService.createSong(song);
		log.info("Song saved id: {}", createdSongId);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
