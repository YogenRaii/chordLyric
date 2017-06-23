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
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.chordLyric.api.controllers.BaseController;
import com.chordLyric.api.exceptions.DataException;
import com.chordLyric.api.models.common.ErrorCodes;
import com.chordLyric.api.models.impl.User;
import com.chordLyric.api.models.interfaces.impl.UserRequest;
import com.chordLyric.api.models.types.RoleType;
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
public class UserController implements BaseController<User> {
	
	private final UserService userService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<User> createUser(
			@ApiParam(value = "Request Body for User", required = true) @Valid @RequestBody UserRequest userRequest,
			BindingResult bindingResult,
			@ApiParam(value = "Token with format 'Bearer Token'", required = true) @RequestHeader("Authorization") final String authorization) {
		if (bindingResult.hasErrors()) {
			throw new DataException(ErrorCodes.EXC400.toString(), bindingResult);
		}
		User user = getUserRequest(userRequest);
		User createdUser = this.userService.createUser(user);
		log.info("feedback saved id: {}", createdUser.getId());

		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

/*	@GetMapping(value = "/auth/me", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<UserResponse> getUser(
			@ApiParam(value = "Token with format 'Bearer Token'", required = true) @RequestHeader("Authorization") final String authorization) {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication auth = context.getAuthentication();

		Optional<User> userOptional = this.userService.findOne(((AuthenticatedUser) auth.getPrincipal()).getId());

		return userOptional.map(user -> {
			final UserResponse userResponse = new UserResponse();
			userResponse.setUser(user);
			userResponse.add(linkTo(methodOn(UserController.class).getUser(authorization)).withSelfRel());
			return new ResponseEntity<>(userResponse, HttpStatus.OK);
		}).orElseThrow(() -> new NotFoundException(ErrorCodes.EXC404.toString(), null));

	}*/

	/**
	 * @param userRequest
	 * @return User object
	 */
	private User getUserRequest(UserRequest userRequest) {
		User user = new User();
		user.setEmail(userRequest.getEmail());
		user.setUsername(userRequest.getUsername());
		user.setFirstName(userRequest.getFirstName());
		user.setLastName(userRequest.getLastName());
		user.setDateCreated(new Date());
		user.setRole(RoleType.ROLE_CONTRIBUTER);
		String id = UUID.randomUUID().toString();
		/*String token = JwtTokenGenerator.generateToken(new JwtUserDto(id,
				userRequest.getFirstName() + "-" + userRequest.getLastName(), 
				RoleType.ROLE_MEMBER.toString()), 
				secret);*/
		user.setId(id);
		user.setToken(UUID.randomUUID().toString());
		return user;
	}

}
