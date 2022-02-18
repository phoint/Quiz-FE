package com.fa.training.group01.dao;

import java.util.List;

import com.fa.training.group01.domain_model.Section;

public interface ISectionDAO {
	Section save(Section theSection);
	
	List<Section> findAllByPart(int partId);
}
