package com.chordLyric.api.security.utils;


import java.util.UUID;

import com.chordLyric.api.models.types.RoleType;
import com.chordLyric.api.security.transfer.JwtUserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Yogen
 *
 */
public class JwtTokenGenerator {
    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. 
     * These properties are taken from the specified user object. 
     * Tokens validity is infinite.
     *
     * @param u the user for which the token will be generated
     * @return the JWT token
     */
    public static String generateToken(JwtUserDto u, String secret) {
        Claims claims = Jwts.claims().setSubject(u.getUsername());
        claims.put("userId", u.getId() + "");
        claims.put("role", u.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    
    /*public static void main(String[] args) {
    	JwtUserDto user = new JwtUserDto(UUID.randomUUID().toString(), "admin", RoleType.ROLE_CONTRIBUTER.toString());
    	String secret = "my-very-secret-key";
    	
    	String token = generateToken(user, secret);
    	System.out.println("---------------" + token + "-----------------------");
    }*/

}
