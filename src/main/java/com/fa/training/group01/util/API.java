package com.fa.training.group01.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class API {
	public static final String HOST = "http://localhost:9090";

	public static final String USER_MODULE = "/user";

	@UtilityClass
	public static class Public {
		public static final String REGISTER = "/register";
		public static final String AUTH_TOKEN = "/login";
	}

	@UtilityClass
	public static class User {
		public static final String ACCOUNT = "/account";
	}
}
