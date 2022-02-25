package com.fa.training.group01.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import com.fa.training.group01.dao.IQuizTakenDAO;
import com.fa.training.group01.dao.IQuizTakerDAO;
import com.fa.training.group01.domain_model.QuizTaken;
import com.fa.training.group01.domain_model.QuizTaker;
import com.fa.training.group01.service.IQuizTakerService;
import com.fa.training.group01.util.API;

@Service
public class QuizTakerService implements IQuizTakerService {

	@Autowired
	IQuizTakerDAO quizTakerDAO;
	
	@Autowired
	IQuizTakenDAO quizTakenDAO;
	
	@Override
	public QuizTaker findById(int userId) {
		QuizTaker quizTaker = quizTakerDAO.findById(userId, API.QuizTaker.QUIZ_TAKER, new ParameterizedTypeReference<EntityModel<QuizTaker>>() {
		});
		List<QuizTaken> quizTakens = quizTakenDAO.findAllByParent(userId, API.QuizTaker.QUIZ_TAKEN, new ParameterizedTypeReference<CollectionModel<QuizTaken>>() {
		});
		
		if (quizTakens != null) {
			quizTaker.setQuizTaken(quizTakens);
		}
		
		return quizTaker;
	}
	
	@Override
	public void addQuizTaken(QuizTaker quizTaker) {
		quizTakerDAO.addChildren(quizTaker, quizTaker.getQuizTaken(), API.QuizTaker.QUIZ_TAKEN, API.QuizTaken.TAKEN);
	}

}
