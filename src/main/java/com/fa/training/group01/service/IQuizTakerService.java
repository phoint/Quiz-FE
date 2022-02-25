package com.fa.training.group01.service;

import com.fa.training.group01.domain_model.QuizTaker;

public interface IQuizTakerService {
	QuizTaker findById(int userId);
	
	void addQuizTaken(QuizTaker quizTaker);
}
