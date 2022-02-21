package com.fa.training.group01.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import com.fa.training.group01.dao.IAnswerDAO;
import com.fa.training.group01.dao.IPartDAO;
import com.fa.training.group01.dao.IQuestionDAO;
import com.fa.training.group01.dao.ISectionDAO;
import com.fa.training.group01.domain_model.Answer;
import com.fa.training.group01.domain_model.Part;
import com.fa.training.group01.domain_model.Question;
import com.fa.training.group01.domain_model.Section;
import com.fa.training.group01.service.IPartService;
import com.fa.training.group01.util.API;

@Service
public class PartService implements IPartService {

	@Autowired
	IPartDAO partDAO;

	@Autowired
	ISectionDAO sectionDAO;

	@Autowired
	IQuestionDAO questionDAO;

	@Autowired
	IAnswerDAO answerDAO;

	@Override
	public Part save(Part part) {
		return partDAO.save(part, API.PART_MODULE,
				new ParameterizedTypeReference<EntityModel<Part>>() {
				});
	}

	@Override
	public void addSection(Part part) {
		partDAO.addChild(part, part.getSections(), API.Part.SECTION,
				API.Section.SECTION);
	}

	@Override
	public Part update(Part part) {
		return partDAO.update(part, API.Part.PART,
				new ParameterizedTypeReference<EntityModel<Part>>() {
				});
	}

	@Override
	public void updateAll(List<Part> parts) {
		for (Part part : parts) {
			partDAO.update(part, API.Part.PART,
					new ParameterizedTypeReference<EntityModel<Part>>() {
					});
		}
	}

	@Override
	public Part findById(int id) {
		Part part = partDAO.findById(id, API.Part.PART,
				new ParameterizedTypeReference<EntityModel<Part>>() {
				});
		List<Section> sections = sectionDAO.findAllByParent(id,
				API.Part.SECTION,
				new ParameterizedTypeReference<CollectionModel<Section>>() {
				});

		for (Section section : sections) {
			List<Question> questions = questionDAO
					.findAllByParent(section.getId(), API.Section.QUESTION,
							new ParameterizedTypeReference<CollectionModel<Question>>() {
							});

			for (Question question : questions) {
				List<Answer> answers = answerDAO.findAllByParent(
						question.getId(), API.Question.ANSWER,
						new ParameterizedTypeReference<CollectionModel<Answer>>() {
						});
				if (answers != null) {
					question.setAnswers(answers);
				}
			}

			if (questions != null) {
				section.setQuestions(questions);
			}

		}

		if (sections != null) {
			part.setSections(sections);
		}
		return part;
	}

	@Override
	public List<Part> findAllByQuiz(int quizId) {
		List<Part> parts = partDAO.findAllByParent(quizId, API.Quiz.PART,
				new ParameterizedTypeReference<CollectionModel<Part>>() {
				});
		return parts;
	}
}
