package com.chordLyric.api.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.chordLyric.api.security.exceptions.JwtTokenExpiredException;
import com.chordLyric.api.security.exceptions.JwtTokenMalformedException;
import com.chordLyric.api.security.exceptions.JwtUserNotFoundException;
import com.chordLyric.api.security.models.AuthenticatedUser;
import com.chordLyric.api.security.models.JwtAuthenticationToken;
import com.chordLyric.api.security.transfer.JwtUserDto;
import com.chordLyric.api.security.utils.JwtTokenValidator;
import com.chordLyric.api.services.UserService;


/**
 * @author Yogen
 *
 */
@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private JwtTokenValidator jwtTokenValidator;
	
	@Autowired
	private UserService userService;
	
	@Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    	//no need implementation as no additional authentication is required
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String token = jwtAuthenticationToken.getToken();

        JwtUserDto parsedUser = jwtTokenValidator.parseToken(token);
        
        if (parsedUser == null) {
            throw new JwtTokenMalformedException("JWT token is not valid");
        }
        
        //validate against stored user
        if(!this.userService.exists(parsedUser.getId())) {
        	throw new JwtUserNotFoundException("User with JWT token not found");
        }

        List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(parsedUser.getRole());

        return new AuthenticatedUser(parsedUser.getId(), parsedUser.getUsername(), token, authorityList);
    }


}
