package com.fa.training.group01.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fa.training.group01.payload.LoginRequest;
import com.fa.training.group01.security.AuthenciationToken;
import com.fa.training.group01.service.IAuthService;
import com.fa.training.group01.util.API;

@Service
public class AuthService implements IAuthService {
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public AuthenciationToken getAuthToken(LoginRequest loginRequest) {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON_UTF8);
		ResponseEntity<AuthenciationToken> authResponse = restTemplate.postForEntity(API.HOST + API.Public.AUTH_TOKEN,
				loginRequest, AuthenciationToken.class);
		return authResponse.getBody();
	}

}
