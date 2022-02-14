package com.fa.training.group01.domain_model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
	ADMIN(RoleName.ADMIN), STUDENT(RoleName.STUDENT);

	@JsonValue
	public final String name;

	private Role(String name) {
		this.name = name;
	}

	@JsonCreator
	public static Role forValues(@JsonProperty("id") Integer id, @JsonProperty("name") String name) {
		for (Role role : Role.values()) {
			if (role.name.equals(name)) {
				return role;
			}
		}
		return null;
	}

	public static class RoleName {
		public static final String STUDENT = "ROLE_STUDENT";
		public static final String ADMIN = "ROLE_ADMIN";
	}

}
