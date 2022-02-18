package com.fa.training.group01.payload;

import lombok.Data;

@Data
public class UpdatePasswordRequest {
	private String username;
	private String currentPassword;
	private String newPassword;
	private String confirmNewPassword;
}
