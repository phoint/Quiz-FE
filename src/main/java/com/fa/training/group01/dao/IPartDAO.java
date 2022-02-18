package com.fa.training.group01.dao;

import java.util.List;

import com.fa.training.group01.domain_model.Part;

public interface IPartDAO {
	Part save(Part part);
	
	void addSection(Part part);
	
	Part update(Part part);
	
	List<Part> findAllByQuiz(int quizId);
	
	Part findById(int partId);
}
