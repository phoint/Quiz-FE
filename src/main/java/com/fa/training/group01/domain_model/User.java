package com.fa.training.group01.domain_model;

import lombok.Data;

@Data
public class User extends Model {
	private Integer id;
	private String email;
	private String name;
	private String password;
	private Role role;
	private boolean active;
	private String keyToken;
}
