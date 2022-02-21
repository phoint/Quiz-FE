package com.fa.training.group01.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fa.training.group01.domain_model.User;
import com.fa.training.group01.payload.UpdatePasswordRequest;
import com.fa.training.group01.security.AuthenciationToken;
import com.fa.training.group01.service.IUserService;
import com.fa.training.group01.util.API;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService implements IUserService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public User getByEmail(String email) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(API.USER_MODULE + "/" + API.User.ACCOUNT)
				// Add query parameter
				.queryParam("email", email);
		ResponseEntity<User> userResponse = restTemplate.getForEntity(API.HOST + "/" + builder.build().toUri(),
				User.class);
		return userResponse.getBody();
	}

	@Override
	public User getByAuthenciation(AuthenciationToken authenciationToken) {
		ResponseEntity<User> userResponse = restTemplate.getForEntity(API.HOST + API.USER_MODULE + API.User.ACCOUNT,
				User.class);
		return userResponse.getBody();
	}

	@Override
	public boolean existsResetPasswordToken(String token) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(API.Public.EXISTS_RESET_PASSWORD_TOKEN)
					// Add query parameter
					.queryParam("token", token);
			return Boolean.valueOf(restTemplate.getForObject(builder.toUriString(), String.class));
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}

	public ResponseEntity<String> updatePassword(UpdatePasswordRequest updatePasswordRequest) {
		try {
			ResponseEntity<String> response = restTemplate.postForEntity(API.USER_MODULE + API.User.UPDATE_PASSWORD,
					updatePasswordRequest, String.class);
			return response;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}

}
