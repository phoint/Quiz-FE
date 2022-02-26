package com.fa.training.group01.service;

import com.fa.training.group01.domain_model.AnswerTaken;

public interface IAnswerTakenService {
	AnswerTaken save(AnswerTaken answerTaken);

	void addInfo(AnswerTaken answerTaken, Integer questionId, Integer answerId);
	
	AnswerTaken findInfo(AnswerTaken answerTaken);
}
