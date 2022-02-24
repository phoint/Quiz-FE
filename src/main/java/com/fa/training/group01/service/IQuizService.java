package com.fa.training.group01.service;

import java.net.URI;
import java.util.List;

import com.fa.training.group01.domain_model.Quiz;

public interface IQuizService {
	Quiz save(Quiz quiz);
	
	void addPart(Quiz quiz);
	
	Quiz update(Quiz quiz);

	List<Quiz> findAll();

	Quiz findById(int id);
	
	Quiz findFullQuiz(int id);
	
	int countQuestion(int quizId); 
}
