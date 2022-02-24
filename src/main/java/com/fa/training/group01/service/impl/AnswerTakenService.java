package com.fa.training.group01.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import com.fa.training.group01.dao.IAnswerDAO;
import com.fa.training.group01.dao.IAnswerTakenDAO;
import com.fa.training.group01.dao.IQuestionDAO;
import com.fa.training.group01.domain_model.Answer;
import com.fa.training.group01.domain_model.AnswerTaken;
import com.fa.training.group01.domain_model.Question;
import com.fa.training.group01.service.IAnswerTakenService;
import com.fa.training.group01.util.API;

@Service
public class AnswerTakenService implements IAnswerTakenService {

	@Autowired
	IAnswerTakenDAO answerTakenDAO;

	@Autowired
	IAnswerDAO answerDAO;
	
	@Autowired
	IQuestionDAO questionDAO;

	@Override
	public AnswerTaken save(AnswerTaken answerTaken) {
		return answerTakenDAO.save(answerTaken, API.ANSWER_TAKEN_MODULE,
				new ParameterizedTypeReference<EntityModel<AnswerTaken>>() {
				});
	}

	@Override
	public void addInfo(AnswerTaken answerTaken, Integer questionId, Integer answerId) {
		addQuestion(answerTaken, questionId);
		if (answerId != null) {
			addAnswer(answerTaken, answerId);
		}
	}

	@Override
	public AnswerTaken findInfo(AnswerTaken answerTaken) {
		Answer answer = findAnswer(answerTaken.getId());
		Question question = findQuestion(answerTaken.getId());
		answerTaken.setQuestion(question);
		answerTaken.setAnswer(answer);
		return answerTaken;
	}

	void addQuestion(AnswerTaken answerTaken, int questionId) {
		answerTakenDAO.addChild(answerTaken, questionId, API.AnswerTaken.QUESTION_TAKEN, API.Question.QUESTION);
	}

	void addAnswer(AnswerTaken answerTaken, int answerId) {
		answerTakenDAO.addChild(answerTaken, answerId, API.AnswerTaken.ANSWER_TAKEN, API.Answer.ANSWER);
	}
	
	Answer findAnswer(int answerTakenId) {
		return answerDAO.findParent(answerTakenId, API.AnswerTaken.ANSWER_TAKEN, new ParameterizedTypeReference<EntityModel<Answer>>() {
		});
	}
	
	Question findQuestion(int answerTakenId) {
		return questionDAO.findParent(answerTakenId, API.AnswerTaken.QUESTION_TAKEN, new ParameterizedTypeReference<EntityModel<Question>>() {
		});
	}
}
