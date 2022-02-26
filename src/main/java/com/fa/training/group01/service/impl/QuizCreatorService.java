package com.fa.training.group01.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import com.fa.training.group01.dao.IQuizCreatorDAO;
import com.fa.training.group01.dao.IQuizDAO;
import com.fa.training.group01.domain_model.Quiz;
import com.fa.training.group01.domain_model.QuizCreator;
import com.fa.training.group01.service.IQuizCreatorService;
import com.fa.training.group01.util.API;

@Service
public class QuizCreatorService implements IQuizCreatorService {

	@Autowired
	IQuizCreatorDAO quizCreatorDAO;
	
	@Autowired
	IQuizDAO quizDAO;
	
	@Override
	public QuizCreator findById(int userId) {
		QuizCreator admin = quizCreatorDAO.findById(userId, API.QuizCreator.QUIZ_CREATOR, new ParameterizedTypeReference<EntityModel<QuizCreator>>() {
		});
		List<Quiz> zzesCreated = quizDAO.findAllByParent(userId, API.QuizCreator.QUIZ, new ParameterizedTypeReference<CollectionModel<Quiz>>() {
		});
		
		if (zzesCreated != null) {
			admin.setQuizzes(zzesCreated);
		}
		
		return admin;
	}

	@Override
	public void addQuiz(QuizCreator user) {
		quizCreatorDAO.addChildren(user, user.getQuizzes(), API.QuizCreator.QUIZ, API.Quiz.QUIZ);
	}

}
