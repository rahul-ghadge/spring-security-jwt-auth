package com.arya.security.jwt.utils;

public class ConstantUtils {
	
	private ConstantUtils() {
	}

	public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60;
	public static final String SIGNING_KEY = "!Y6C;Ft@)Ak3}vsnP7(Bow_7vQ[y&[";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String AUTHORIZATION = "Authorization";
}
