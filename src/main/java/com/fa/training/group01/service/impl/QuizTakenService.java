package com.fa.training.group01.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import com.fa.training.group01.dao.IAnswerTakenDAO;
import com.fa.training.group01.dao.IQuizDAO;
import com.fa.training.group01.dao.IQuizTakenDAO;
import com.fa.training.group01.domain_model.AnswerTaken;
import com.fa.training.group01.domain_model.Quiz;
import com.fa.training.group01.domain_model.QuizTaken;
import com.fa.training.group01.service.IAnswerTakenService;
import com.fa.training.group01.service.IQuizTakenService;
import com.fa.training.group01.util.API;

@Service
public class QuizTakenService implements IQuizTakenService {

	@Autowired
	private IQuizTakenDAO quizTakenDAO;

	@Autowired
	private IAnswerTakenDAO answerTakenDAO;

	@Autowired
	private IAnswerTakenService answerTakenService;

	@Autowired
	private IQuizDAO quizDAO;

	@Override
	public QuizTaken save(QuizTaken quizTaken) {
		return quizTakenDAO.save(quizTaken, API.QUIZ_TAKEN_MODULE,
				new ParameterizedTypeReference<EntityModel<QuizTaken>>() {
				});
	}

	@Override
	public void addAnswerTaken(QuizTaken quizTaken) {
		quizTakenDAO.addChildren(quizTaken, quizTaken.getAnswerTaken(), API.QuizTaken.ANSWER_TAKEN,
				API.AnswerTaken.TAKEN);
	}

	@Override
	public void addQuiz(QuizTaken quizTaken, int quizId) {
		quizTakenDAO.addChild(quizTaken, quizId, API.QuizTaken.QUIZ, API.Quiz.QUIZ);
	}

	@Override
	public QuizTaken findById(int id) {
		QuizTaken quizTaken = quizTakenDAO.findById(id, API.QuizTaken.TAKEN,
				new ParameterizedTypeReference<EntityModel<QuizTaken>>() {
				});
		List<AnswerTaken> answerTakens = answerTakenDAO.findAllByParent(id, API.QuizTaken.ANSWER_TAKEN,
				new ParameterizedTypeReference<CollectionModel<AnswerTaken>>() {
				});
		Quiz quiz = quizDAO.findParent(id, API.QuizTaken.QUIZ, new ParameterizedTypeReference<EntityModel<Quiz>>() {
		});

		quizTaken.setQuizId(quiz.getId());

		for (AnswerTaken answerTaken : answerTakens) {
			answerTakenService.findInfo(answerTaken);
		}
		if (answerTakens != null) {
			quizTaken.setAnswerTaken(answerTakens);
		}

		return quizTaken;
	}

	@Override
	public QuizTaken calculateScore(QuizTaken quizTaken) {
		int scored = 0;
		int maxScored = 0;
		for (AnswerTaken answerTaken : quizTaken.getAnswerTaken()) {
			maxScored += answerTaken.getQuestion().getScore();
			if (answerTaken.getAnswer().isCorrect()) {
				scored += answerTaken.getQuestion().getScore();
			}
		}
		quizTaken.setScored(scored);
		quizTaken.setMaxScore(maxScored);

		return quizTaken;
	}
}
