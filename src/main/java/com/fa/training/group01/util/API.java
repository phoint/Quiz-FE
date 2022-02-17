package com.fa.training.group01.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class API {
	public static final String HOST = "http://localhost:9090";

	public static final String USER_MODULE = "/user";
	public static final String QUIZ_MODULE = "/quizzes";

	@UtilityClass
	public static class Public {
		public static final String REGISTER = "/register";
		public static final String AUTH_TOKEN = "/login";
	}

	@UtilityClass
	public static class User {
		public static final String ACCOUNT = "/account";
	}
	
	public static class Quiz {
		public static final String PART = "/parts";
		public static final String SECTION = "/sections";
		public static final String QUESTION = "/questions";
		public static final String ANSWER = "/answers";
	}
}
