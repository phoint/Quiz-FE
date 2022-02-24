package com.fa.training.group01.payload;

import lombok.Data;

@Data
public class UpdateUserRequest {
	private Integer id;
	private String password;
	private String role;
	private String active;
}
