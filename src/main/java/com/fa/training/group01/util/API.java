package com.fa.training.group01.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class API {
	public static final String HOST = "http://localhost:9090";
	public static final String ADMIN_AREA_PATH = "/admin";
	public static final String USER_MODULE = "/user";
	public static final String TOPIC_MODULE = "/api/topics";
	public static final String QUIZ_MODULE = "/api/quizzes";
	public static final String PART_MODULE = "/api/parts";
	public static final String SECTION_MODULE = "/api/sections";
	public static final String QUESTION_MODULE = "/api/questions";
	public static final String ANSWER_MODULE = "/api/answers";
	public static final String QUIZ_TAKEN_MODULE = "/api/quiz-takens";
	public static final String ANSWER_TAKEN_MODULE = "/api/ans-takens";
	public static final String QUIZ_TAKER_MODULE = "/api/users";
	public static final String QUIZ_CREATOR_MODULE = "/api/users";
	

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

		public static final String UPDATE_PROFILE = "/update-profile";
		public static final String ACCOUNT = "/account";
		public static final String UPDATE_PASSWORD = "/update-password";
	}
	
	@UtilityClass
	public static class QuizTaker {
		public static final String QUIZ_TAKER = "/api/users/{userId}";
		public static final String QUIZ_TAKEN = "/api/users/{userId}/take";
	}

	@UtilityClass
	public static class QuizCreator {
		public static final String QUIZ_CREATOR = "/api/users/{userId}";
		public static final String QUIZ = "/api/users/{userId}/create";
	}

	@UtilityClass
	public static class Topic {
		public static final String TOPIC = "/api/topics/{topicId}";
		public static final String Quiz = "/api/topics/{topicId}/quizzes";
	}
	
	@UtilityClass
	public static class Quiz {
		public static final String QUIZ = "/api/quizzes/{quizId}";
		public static final String PART = "/api/quizzes/{quizId}/parts";
		public static final String QUESTION = "/api/quizzes/{quizId}/questions";
		public static final String QUIZ_TAKEN = "/api/quizzes/{quizId}/quiz-takens";
	}

	@UtilityClass
	public static class Part {
		public static final String PART = "/api/parts/{partId}";
		public static final String SECTION = "/api/parts/{partId}/sections";
		public static final String REL = "parts";
	}

	@UtilityClass
	public static class Section {
		public static final String SECTION = "/api/sections/{sectionId}";
		public static final String QUESTION = "/api/sections/{sectionId}/questions";
	}

	@UtilityClass
	public static class Question {
		public static final String QUESTION = "/api/questions/{questionId}";
		public static final String ANSWER = "/api/questions/{questionId}/answers";
		public static final String QUESTION_TAKEN = "/api/questions/{questionId}/ans-takens";
	}

	@UtilityClass
	public static class Answer {
		public static final String ANSWER = "/api/answers/{answerId}";
		public static final String ANSWER_TAKEN = "/api/answers/{answerId}/ans-takens";
	}

	@UtilityClass
	public static class QuizTaken {
		public static final String TAKEN = "/api/quiz-takens/{quizTakenId}";
		public static final String ANSWER_TAKEN = "/api/quiz-takens/{quizTakenId}/ans-takens";
		public static final String QUIZ = "/api/quiz-takens/{quizTakenId}/quiz";
	}

	@UtilityClass
	public static class AnswerTaken {
		public static final String TAKEN = "/api/ans-takens/{ansTakenId}";
		public static final String ANSWER_TAKEN = "/api/ans-takens/{ansTakenId}/answer";
		public static final String QUESTION_TAKEN = "/api/ans-takens/{ansTakenId}/question";
	}

}
