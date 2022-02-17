package com.fa.training.group01.service;

import java.util.List;

import com.fa.training.group01.domain_model.Part;

public interface IPartService {
	Part save(Part part);
	
	Part update(Part part);
	
	void updateAll(List<Part> parts);

//	List<Part> findAll();
//
//	Part findById(int id);
}
