package com.fa.training.group01.service;

import com.fa.training.group01.domain_model.QuizCreator;

public interface IQuizCreatorService {
	QuizCreator findById(int userId);
	
	void addQuiz(QuizCreator user);
}
