package com.fa.training.group01.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class API {
	public static final String HOST = "http://localhost:9090";

	public static final String USER_MODULE = "/user";
	public static final String QUIZ_MODULE = "/quizzes";
	public static final String PART_MODULE = "/parts";

	@UtilityClass
	public static class Public {
		public static final String REGISTER = "/register";
		public static final String AUTH_TOKEN = "/login";
	}

	@UtilityClass
	public static class User {
		public static final String ACCOUNT = "/account";
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
		public static final String SECTION = "/section/{sectionId}";
		public static final String QUESTION = "/section/{sectionId}/questions";
	}

	@UtilityClass
	public static class Question {
		public static final String QUESTION = "/questions/{questionId}";
		public static final String ANSWER = "/questions/{questionId}/answers";
	}
	
	
	
}