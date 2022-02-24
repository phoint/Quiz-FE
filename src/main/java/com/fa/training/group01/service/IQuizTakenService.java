package com.fa.training.group01.service;

import com.fa.training.group01.domain_model.QuizTaken;

public interface IQuizTakenService {
	QuizTaken save(QuizTaken quizTaken);
	
	void addAnswerTaken(QuizTaken quizTaken);
	
	void addQuiz(QuizTaken quizTaken, int quizId);
	
	QuizTaken findById(int id);
	
	QuizTaken calculateScore(QuizTaken quizTaken);
}
