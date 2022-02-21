package com.fa.training.group01.service;

import java.util.List;

import com.fa.training.group01.domain_model.Section;

public interface ISectionService {
	Section save(Section theSection);

	Section update(Section section);

	void updateAll(List<Section> sections);

	Section findById(int sectionId);
	
	void addQuestion(Section section);
}
