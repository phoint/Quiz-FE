package com.fa.training.group01.payload;

import java.util.Optional;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class UserPageRequest {
	private Integer pageIndex;
	private String email;
	private String sortBy;
}
