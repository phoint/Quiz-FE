package com.fa.training.group01.service;

import java.util.List;

import com.fa.training.group01.domain_model.Part;

public interface IPartService {
	Part save(Part part);

	void addSection(Part part);

	Part update(Part part);

	void updateAll(List<Part> parts);

	Part findById(int id);
	
	List<Part> findAllByQuiz(int quizId);

	// List<Part> findAll();
//
}
