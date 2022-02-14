package com.fa.training.group01.service;

import com.fa.training.group01.payload.LoginRequest;
import com.fa.training.group01.security.AuthenciationToken;

public interface IAuthService {
	AuthenciationToken getAuthToken(LoginRequest loginRequest);
}
