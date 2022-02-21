package com.fa.training.group01.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import com.fa.training.group01.dao.IQuestionDAO;
import com.fa.training.group01.domain_model.Answer;
import com.fa.training.group01.domain_model.Question;
import com.fa.training.group01.service.IQuestionService;
import com.fa.training.group01.util.API;

@Service
public class QuestionService implements IQuestionService {

	@Autowired
	IQuestionDAO questionDAO;

	private ParameterizedTypeReference<EntityModel<Question>> entityRes = new ParameterizedTypeReference<EntityModel<Question>>() {
	};

	private ParameterizedTypeReference<CollectionModel<Question>> collectionRes = new ParameterizedTypeReference<CollectionModel<Question>>() {
	};

	@Override
	public Question save(Question question) {
		return questionDAO.save(question, API.QUESTION_MODULE, entityRes);
	}

	@Override
	public Question update(Question question) {
		return questionDAO.update(question, API.Question.QUESTION, entityRes);
	}
	
	@Override
	public void addAnswer(Question question) {
		questionDAO.addChild(question, question.getAnswers(), API.Question.ANSWER, API.Answer.ANSWER);
	}

	@Override
	public Question findById(int questionId) {
		return questionDAO.findById(questionId, API.Question.QUESTION,
				entityRes);
	}
	
	@Override
	public Question createBlank() {
		Question theQuestion = new Question();
		for (int i = 0; i < 4; i++) {
			theQuestion.getAnswers().add(new Answer());
		}
		return theQuestion;
	}

}
