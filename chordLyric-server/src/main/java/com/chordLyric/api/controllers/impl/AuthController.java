package com.chordLyric.api.controllers.impl;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chordLyric.api.controllers.BaseController;
import com.chordLyric.api.exceptions.DataException;
import com.chordLyric.api.exceptions.NotFoundException;
import com.chordLyric.api.models.common.ErrorCodes;
import com.chordLyric.api.models.impl.User;
import com.chordLyric.api.models.interfaces.impl.LoginRequest;
import com.chordLyric.api.models.interfaces.impl.LoginSuccessResponse;
import com.chordLyric.api.models.interfaces.impl.UserRequest;
import com.chordLyric.api.security.transfer.JwtUserDto;
import com.chordLyric.api.security.utils.JwtTokenGenerator;
import com.chordLyric.api.services.UserService;

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
public class AuthController implements BaseController<User> {
	@Value("${jwt.secret}")
	private String secret;

	private final UserService userService;

	@PostMapping(value = "/auth/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<LoginSuccessResponse> authorize(
			@ApiParam(value = "Request Body for User", required = true) @Valid @RequestBody LoginRequest userRequest,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new DataException(ErrorCodes.EXC400.toString(), bindingResult);
		}
		
		User userInDB = this.userService.findByEmailAndPassword(userRequest.getEmail(), userRequest.getPassword());
		
		String token = JwtTokenGenerator.generateToken(new JwtUserDto(userInDB.getId(),
				userInDB.getEmail(), 
				userInDB.getRole().toString()), 
				secret);
		
		log.debug("User with username: {} is authenticated.", userInDB.getUsername());

		final LoginSuccessResponse loginSuccessResponse = new LoginSuccessResponse(userInDB.getId(), token);
		loginSuccessResponse.add(linkTo(methodOn(AuthController.class).authorize(userRequest, bindingResult)).withSelfRel());
		return new ResponseEntity<>(loginSuccessResponse, HttpStatus.OK);
	}
}
