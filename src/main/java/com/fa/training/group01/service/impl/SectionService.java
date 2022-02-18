package com.fa.training.group01.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fa.training.group01.dao.ISectionDAO;
import com.fa.training.group01.domain_model.Section;
import com.fa.training.group01.service.ISectionService;

@Service
public class SectionService implements ISectionService {

	@Autowired
	ISectionDAO sectionDAO;

	@Override
	public Section save(Section theSection) {
		return sectionDAO.save(theSection);
	}

}
