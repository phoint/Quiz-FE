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
import com.fa.training.group01.dao.IQuizDAO;
import com.fa.training.group01.dao.ISectionDAO;
import com.fa.training.group01.domain_model.Answer;
import com.fa.training.group01.domain_model.Part;
import com.fa.training.group01.domain_model.Question;
import com.fa.training.group01.domain_model.Quiz;
import com.fa.training.group01.domain_model.Section;
import com.fa.training.group01.service.IQuizService;
import com.fa.training.group01.util.API;

@Service
public class QuizService implements IQuizService {

	@Autowired
	IQuizDAO quizDAO;

	@Autowired
	IPartDAO partDAO;

	@Autowired
	ISectionDAO sectionDAO;

	@Autowired
	IQuestionDAO questionDAO;

	@Autowired
	IAnswerDAO answerDAO;

	@Override
	public Quiz save(Quiz quiz) {
		return quizDAO.save(quiz, API.QUIZ_MODULE, new ParameterizedTypeReference<EntityModel<Quiz>>() {
		});
	}

	@Override
	public void addPart(Quiz quiz) {
		quizDAO.addChildren(quiz, quiz.getParts(), API.Quiz.PART, API.Part.PART);
	}

	@Override
	public Quiz update(Quiz quiz) {
		return quizDAO.update(quiz, API.Quiz.QUIZ, new ParameterizedTypeReference<EntityModel<Quiz>>() {
		});
	}

	@Override
	public List<Quiz> findAll() {
		return quizDAO.findAll(API.QUIZ_MODULE, new ParameterizedTypeReference<CollectionModel<Quiz>>() {
		});
	}

	@Override
	public Quiz findById(int id) {
		Quiz quiz = quizDAO.findById(id, API.Quiz.QUIZ, new ParameterizedTypeReference<EntityModel<Quiz>>() {
		});
		List<Part> parts = partDAO.findAllByParent(id, API.Quiz.PART,
				new ParameterizedTypeReference<CollectionModel<Part>>() {
				});

		if (parts != null) {
			quiz.setParts(parts);
		}

		return quiz;
	}

	@Override
	public Quiz findFullQuiz(int id) {
		Quiz quiz = quizDAO.findById(id, API.Quiz.QUIZ, new ParameterizedTypeReference<EntityModel<Quiz>>() {
		});
		List<Part> parts = partDAO.findAllByParent(id, API.Quiz.PART,
				new ParameterizedTypeReference<CollectionModel<Part>>() {
				});
		for (Part part : parts) {
			List<Section> sections = sectionDAO.findAllByParent(part.getId(), API.Part.SECTION,
					new ParameterizedTypeReference<CollectionModel<Section>>() {
					});
			for (Section section : sections) {
				List<Question> questions = questionDAO.findAllByParent(section.getId(), API.Section.QUESTION,
						new ParameterizedTypeReference<CollectionModel<Question>>() {
						});

				for (Question question : questions) {
					List<Answer> answers = answerDAO.findAllByParent(question.getId(), API.Question.ANSWER,
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
		}

		if (parts != null) {
			quiz.setParts(parts);
		}

		return quiz;
	}

	@Override
	public int countQuestion(int quizId) {
		List<Question> questions = questionDAO.findAllByParent(quizId, API.Quiz.QUESTION,
				new ParameterizedTypeReference<CollectionModel<Question>>() {
				});

		return (questions == null || questions.isEmpty()) ? 0 : questions.size();
	}

}
