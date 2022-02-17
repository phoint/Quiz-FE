package com.fa.training.group01.dao;

import java.net.URI;
import java.util.List;

import com.fa.training.group01.domain_model.Quiz;

public interface IQuizDAO {
	Quiz save(Quiz quiz);

	List<Quiz> findAll();

	Quiz findById(int id);
}
