package com.fa.training.group01.util;

import java.util.Base64;

import org.springframework.http.HttpHeaders;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthenciationHelper {

	public static final String BASIC_AUTH_USERNAME = "client";
	public static final String BASIC_AUTH_PASSWORD = "9999";

	public static String createAuthCredential(String username, String password) {
		return Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
	}

	public static HttpHeaders basicAuthorizationHeader(String credential) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, "Basic " + credential);
		return headers;
	}
}
