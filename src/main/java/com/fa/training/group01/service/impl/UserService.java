package com.fa.training.group01.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fa.training.group01.domain_model.User;
import com.fa.training.group01.payload.UpdatePasswordRequest;
import com.fa.training.group01.payload.UpdateUserProfileRequest;
import com.fa.training.group01.payload.UpdateUserRequest;
import com.fa.training.group01.payload.UserPageRequest;
import com.fa.training.group01.payload.jpa.EntityPageResponse;
import com.fa.training.group01.security.AuthenciationToken;
import com.fa.training.group01.service.IUserService;
import com.fa.training.group01.util.API;
import com.fa.training.group01.util.FileHelper;
import com.fasterxml.jackson.core.type.TypeReference;
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

	@Override
	public EntityPageResponse<User> findAll() {
		try {

			ResponseEntity<String> response = restTemplate.getForEntity(
					API.ADMIN_AREA_PATH + API.USER_MODULE + API.User.AdminArea.ACCOUNT_LIST, String.class);
			EntityPageResponse<User> jsonResponse = objectMapper.readValue(response.getBody(),
					new TypeReference<EntityPageResponse<User>>() {
					});
			System.out.println(jsonResponse);
			return jsonResponse;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}

	@Override
	public EntityPageResponse<User> findAll(UserPageRequest userPageRequest, int pageSize) {
		try {
			String urlTemplate = UriComponentsBuilder
					.fromUriString(API.ADMIN_AREA_PATH + API.USER_MODULE + API.User.AdminArea.ACCOUNT_LIST)
					.queryParam(UserPageRequest.Fields.email, userPageRequest.getEmail())
					.queryParam(UserPageRequest.Fields.pageIndex, userPageRequest.getPageIndex())
					.queryParam(UserPageRequest.Fields.sortBy, userPageRequest.getSortBy())
					.queryParam("pageSize", pageSize).toUriString();

//			restTemplate.exchange(API.ADMIN_AREA_PATH + API.USER_MODULE + API.User.AdminArea.ACCOUNT_LIST, null)
			ResponseEntity<EntityPageResponse<User>> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, null,
					new ParameterizedTypeReference<EntityPageResponse<User>>() {
					});
			return response.getBody();
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}

	@Override
	public User findOneByID(Integer id) {
		try {
			Map<String, String> urlParams = new HashMap<String, String>();
			urlParams.put("id", "" + id);
			String url = UriComponentsBuilder
					.fromUriString(API.ADMIN_AREA_PATH + API.USER_MODULE + API.User.AdminArea.GET_USER)
					.buildAndExpand(urlParams).toUriString();
			ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);
			return response.getBody();
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}

	}

	@Override
	public ResponseEntity<String> updateUser(UpdateUserRequest updateUserRequest) {
		try {
			return restTemplate.postForEntity(API.ADMIN_AREA_PATH + API.USER_MODULE + API.User.AdminArea.UPDATE_USER,
					updateUserRequest, String.class);
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}

	@Override
	public ResponseEntity<String> updateProfile(UpdateUserProfileRequest updateUserProfileRequest) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

			ByteArrayResource bar = new ByteArrayResource(updateUserProfileRequest.getAvatar().getBytes()) {
				@Override
				public String getFilename() {
					return updateUserProfileRequest.getAvatar().getName();
				}
			};
			body.add(UpdateUserProfileRequest.Fields.avatar, bar);
			body.add(UpdateUserProfileRequest.Fields.name, updateUserProfileRequest.getName());
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
			return restTemplate.postForEntity(API.USER_MODULE + API.User.UPDATE_PROFILE, requestEntity, String.class);
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}

	@Override
	public User findCurrentUser() {
		try {
			ResponseEntity<User> userResponse = restTemplate.getForEntity(API.HOST + API.USER_MODULE + API.User.ACCOUNT,
					User.class);
			return userResponse.getBody();
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}

}
