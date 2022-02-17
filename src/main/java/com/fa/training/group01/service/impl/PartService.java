package com.fa.training.group01.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fa.training.group01.dao.IPartDAO;
import com.fa.training.group01.domain_model.Part;
import com.fa.training.group01.service.IPartService;

@Service
public class PartService implements IPartService {

	@Autowired
	IPartDAO partDAO;

	@Override
	public Part save(Part part) {
		return partDAO.save(part);
	}
	
	@Override
	public Part update(Part part) {
		return partDAO.update(part);
	}
	
	@Override
	public void updateAll(List<Part> parts) {
		for (Part part : parts) {
			partDAO.update(part);
		}
	}
//
//	@Override
//	public List<Part> findAll() {
//		return partDAO.findAll();
//	}
//
//	@Override
//	public Part findById(int id) {
//		return partDAO.findById(id);
//	}

}
