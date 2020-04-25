package com.arya.security.jwt.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.arya.security.jwt.utils.ConstantUtils.*;

import java.io.IOException;
import java.util.Arrays;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		String header = req.getHeader(AUTHORIZATION);
		String userName = null;
		String authToken = null;

		if (header != null && header.startsWith(TOKEN_PREFIX)) {
			// authToken = header.substring(7);
			authToken = header.replace(TOKEN_PREFIX, "");

			try {
				userName = jwtTokenUtil.getUsernameFromToken(authToken);
			} catch (IllegalArgumentException e) {
				logger.error("An error occurred during getting username from token", e);
			} catch (ExpiredJwtException e) {
				logger.warn("The token is expired and not valid anymore", e);
			} catch (SignatureException e) {
				logger.error("Authentication Failed. Username or Password not valid.");
			}
		} else {
			logger.warn("Couldn't find bearer string, will ignore the header");
		}
		
		
		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

			if (jwtTokenUtil.validateToken(authToken, userDetails)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, Arrays.asList(new SimpleGrantedAuthority("USER"), new SimpleGrantedAuthority("ADMIN")));
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
				
				logger.info("authenticated user " + userName + ", setting security context");
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		chain.doFilter(req, res);
	}
}