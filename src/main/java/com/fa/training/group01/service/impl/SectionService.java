package com.fa.training.group01.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import com.fa.training.group01.dao.IQuestionDAO;
import com.fa.training.group01.dao.ISectionDAO;
import com.fa.training.group01.domain_model.Question;
import com.fa.training.group01.domain_model.Section;
import com.fa.training.group01.service.ISectionService;
import com.fa.training.group01.util.API;

@Service
public class SectionService implements ISectionService {

	private ParameterizedTypeReference<EntityModel<Section>> entityRes = new ParameterizedTypeReference<EntityModel<Section>>() {
	};
	private ParameterizedTypeReference<CollectionModel<Section>> collectionRes = new ParameterizedTypeReference<CollectionModel<Section>>() {
	};

	@Autowired
	ISectionDAO sectionDAO;

	@Autowired
	IQuestionDAO questionDAO;

	@Override
	public Section save(Section theSection) {
		return sectionDAO.save(theSection, API.SECTION_MODULE, entityRes);
	}

	@Override
	public Section update(Section section) {
		return sectionDAO.update(section, API.Section.SECTION, entityRes);
	}

	@Override
	public void updateAll(List<Section> sections) {
		for (Section section : sections) {
			sectionDAO.update(section, API.Section.SECTION, entityRes);
		}
	}

	@Override
	public Section findById(int sectionId) {
		Section theSection = sectionDAO.findById(sectionId, API.Section.SECTION,
				entityRes);
		
		List<Question> questions = questionDAO.findAllByParent(sectionId,
				API.Section.QUESTION,
				new ParameterizedTypeReference<CollectionModel<Question>>() {
				});
		
		if (questions != null) {
			theSection.setQuestions(questions);
		}

		return theSection;
	}

	@Override
	public void addQuestion(Section section) {
		sectionDAO.addChildren(section, section.getQuestions(),
				API.Section.QUESTION, API.Question.QUESTION);
	}

}
