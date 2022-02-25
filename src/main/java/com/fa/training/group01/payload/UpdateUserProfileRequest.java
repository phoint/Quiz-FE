package com.fa.training.group01.payload;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class UpdateUserProfileRequest {
	private String name;
	private MultipartFile avatar;
}
