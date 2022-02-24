package com.fa.training.group01.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.fa.training.group01.domain_model.User;
import com.fa.training.group01.payload.UpdatePasswordRequest;
import com.fa.training.group01.payload.jpa.EntityPageResponse;
import com.fa.training.group01.security.AuthenciationToken;

public interface IUserService {
	User getByEmail(String email);

	User getByAuthenciation(AuthenciationToken authenciationToken);

	boolean existsResetPasswordToken(String token);

	ResponseEntity<String> updatePassword(UpdatePasswordRequest updatePasswordRequest);

	EntityPageResponse<User> findAll();
}
