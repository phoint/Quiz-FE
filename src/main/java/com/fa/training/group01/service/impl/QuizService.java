package com.fa.training.group01.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fa.training.group01.dao.IPartDAO;
import com.fa.training.group01.dao.IQuizDAO;
import com.fa.training.group01.domain_model.Part;
import com.fa.training.group01.domain_model.Quiz;
import com.fa.training.group01.service.IQuizService;

@Service
public class QuizService implements IQuizService {

	@Autowired
	IQuizDAO quizDAO;

	@Autowired
	IPartDAO partDAO;

	@Override
	public Quiz save(Quiz quiz) {
		return quizDAO.save(quiz);
	}

	@Override
	public void addPart(Quiz quiz) {
		quizDAO.addPart(quiz);
	}

	@Override
	public Quiz update(Quiz quiz) {
		return quizDAO.update(quiz);
	}

	@Override
	public List<Quiz> findAll() {
		return quizDAO.findAll();
	}

	@Override
	public Quiz findById(int id) {
		Quiz quiz = quizDAO.findById(id);
		List<Part> parts = partDAO.findAllByQuiz(id);

		if (parts != null) {
			quiz.setParts(parts);
		}

		return quiz;
	}

}
