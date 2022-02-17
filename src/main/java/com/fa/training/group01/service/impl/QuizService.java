package com.fa.training.group01.service.impl;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fa.training.group01.dao.IQuizDAO;
import com.fa.training.group01.domain_model.Quiz;
import com.fa.training.group01.service.IQuizService;

@Service
public class QuizService implements IQuizService {

	@Autowired
	IQuizDAO quizDAO;

	@Override
	public Quiz save(Quiz quiz) {
		return quizDAO.save(quiz);
	}

	@Override
	public List<Quiz> findAll() {
		return quizDAO.findAll();
	}

	@Override
	public Quiz findById(int id) {
		return quizDAO.findById(id);
	}

}
