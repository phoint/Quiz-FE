package com.fa.training.group01.payload;

import java.util.Optional;

import lombok.Data;

@Data
public class UserPageRequest {
	private Integer pageIndex;
	private Integer pageSize;
	private String email;
	private String sortBy;

}
