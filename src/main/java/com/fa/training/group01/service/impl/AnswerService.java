package com.fa.training.group01.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import com.fa.training.group01.dao.IAnswerDAO;
import com.fa.training.group01.domain_model.Answer;
import com.fa.training.group01.service.IAnswerService;
import com.fa.training.group01.util.API;

@Service
public class AnswerService implements IAnswerService {

	@Autowired
	IAnswerDAO answerDAO;
	
	@Override
	public Answer save(Answer answer) {
		return answerDAO.save(answer, API.ANSWER_MODULE, new ParameterizedTypeReference<EntityModel<Answer>>() {
		});
	}

	@Override
	public Answer update(Answer answer) {
		return answerDAO.update(answer, API.Answer.ANSWER, new ParameterizedTypeReference<EntityModel<Answer>>() {
		});
	}

}
