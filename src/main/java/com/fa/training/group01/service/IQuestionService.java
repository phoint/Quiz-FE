package com.fa.training.group01.service;

import com.fa.training.group01.domain_model.Question;

public interface IQuestionService {

	Question save(Question question);
	
	Question findById(int questionId);

	Question update(Question question);
	
	void addAnswer(Question question);
	
	Question createBlank();
}
