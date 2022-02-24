package com.fa.training.group01.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class API {
	public static final String HOST = "http://localhost:9090";
	public static final String ADMIN_AREA_PATH = "/admin";
	public static final String USER_MODULE = "/user";
	public static final String QUIZ_MODULE = "/quizzes";
	public static final String PART_MODULE = "/parts";
	public static final String SECTION_MODULE = "/sections";
	public static final String QUESTION_MODULE = "/questions";
	public static final String ANSWER_MODULE = "/answers";

	@UtilityClass
	public static class Public {
		public static final String REGISTER = "/register";
		public static final String AUTH_TOKEN = "/login";
		public static final String EXISTS_RESET_PASSWORD_TOKEN = "/exists-reset-password-token";
	}

	@UtilityClass
	public static class User {
		public static class AdminArea {
			public static final String ACCOUNT_LIST = "/list";
			public static final String GET_USER = "/get/{id}";
			public static final String UPDATE_USER = "/update";
		}

		public static final String ACCOUNT = "/account";
		public static final String UPDATE_PASSWORD = "/update-password";
	}

	@UtilityClass
	public static class Quiz {
		public static final String QUIZ = "/quizzes/{quizId}";
		public static final String PART = "/quizzes/{quizId}/parts";
	}

	@UtilityClass
	public static class Part {
		public static final String PART = "/parts/{partId}";
		public static final String SECTION = "/parts/{partId}/sections";
		public static final String REL = "parts";
	}

	@UtilityClass
	public static class Section {
		public static final String SECTION = "/sections/{sectionId}";
		public static final String QUESTION = "/sections/{sectionId}/questions";
	}

	@UtilityClass
	public static class Question {
		public static final String QUESTION = "/questions/{questionId}";
		public static final String ANSWER = "/questions/{questionId}/answers";
	}

	@UtilityClass
	public static class Answer {
		public static final String ANSWER = "/answers/{answerId}";
	}

}
