package com.cognizant.springlearn.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
	Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

	@GetMapping("/authenticate")
	public Map<String, String> authnticate(@RequestHeader("Authorization") String authHeader) {
		LOGGER.info("START");
		LOGGER.debug("AuthHeader: " + authHeader);
		String user = getUser(authHeader);
		LOGGER.debug(user);
		String token = generateJwt(user);
		LOGGER.debug(token);
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		LOGGER.info("END");
		return map;
	}

	private String getUser(String authHeader) {
		byte[] decode = Base64.getDecoder().decode("dXNlcjpwd2Q=");
		String str = new String(decode);
		String substring = str.substring(0, 4);
		return substring;
	}

	private String generateJwt(String user) {
		JwtBuilder builder = Jwts.builder(); 
		builder.setSubject(user); 
		// Set the token issue time as current time 
		builder.setIssuedAt(new Date()); 
		// Set the token expiry as 40 minutes from now 
		builder.setExpiration(new Date((new Date()).getTime() + 2400000)); 
		builder.signWith(SignatureAlgorithm.HS256, "secret");
		String token = builder.compact();  
		return token; 

	}
}