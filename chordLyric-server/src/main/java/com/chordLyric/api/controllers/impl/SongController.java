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

	@PostMapping(value = "/songs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> createUser(
			@ApiParam(value = "Request Body for User", required = true) @Valid @RequestBody Song song,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new DataException(ErrorCodes.EXC400.toString(), bindingResult);
		}
		String createdSongId = this.songService.createSong(song);
		log.info("feedback saved id: {}", createdSongId);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
