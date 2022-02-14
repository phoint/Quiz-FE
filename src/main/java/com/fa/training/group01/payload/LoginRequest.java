package com.fa.training.group01.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {
	private String username;
	private String password;
	private String authProvider;
	private String accessToken;
}
