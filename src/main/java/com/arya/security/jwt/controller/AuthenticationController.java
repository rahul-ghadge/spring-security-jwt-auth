package com.arya.security.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arya.security.jwt.config.JwtTokenUtil;
import com.arya.security.jwt.model.AuthToken;
import com.arya.security.jwt.model.LoginUser;
import com.arya.security.jwt.model.Response;
import com.arya.security.jwt.model.User;
import com.arya.security.jwt.service.UserService;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	
	@PostMapping(value = "/token")
	public Response<AuthToken> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
		final User user = userService.findOne(loginUser.getUsername());
		final String token = jwtTokenUtil.generateToken(user);
		
		return new Response<>(HttpStatus.OK.value(), HttpStatus.OK.name(), new AuthToken(token, user.getUsername()));
	}

}
