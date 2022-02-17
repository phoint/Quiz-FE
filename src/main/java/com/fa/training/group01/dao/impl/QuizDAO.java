package com.fa.training.group01.dao.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fa.training.group01.dao.IQuizDAO;
import com.fa.training.group01.domain_model.Quiz;
import com.fa.training.group01.util.API;

@Component
public class QuizDAO implements IQuizDAO {

	@Autowired
	@Qualifier(value = "hypermediaRestTemplate")
	RestTemplate template;

	@Override
	public Quiz save(Quiz quiz) {
		EntityModel<Quiz> quizPosting = EntityModel.of(quiz);
		HttpEntity<EntityModel<Quiz>> request = new HttpEntity<EntityModel<Quiz>>(quizPosting);
		ResponseEntity<EntityModel<Quiz>> resObject = template.exchange(API.HOST + API.QUIZ_MODULE, HttpMethod.POST,
				request, new ParameterizedTypeReference<EntityModel<Quiz>>() {
				});
		return resObject.getBody().getContent();
	}

	@Override
	public List<Quiz> findAll() {
		ResponseEntity<CollectionModel<Quiz>> quizResponse = template.exchange(API.HOST + API.QUIZ_MODULE,
				HttpMethod.GET, null, new ParameterizedTypeReference<CollectionModel<Quiz>>() {
				});
		List<Quiz> quizzes = new ArrayList<Quiz>(quizResponse.getBody().getContent());

		return quizzes;
	}

	@Override
	public Quiz findById(int id) {
		UriComponents uriComponents = UriComponentsBuilder.fromUriString(API.HOST).path(API.QUIZ_MODULE).path("/{id}")
				.build();
		URI quizURI = uriComponents.expand(Integer.toString(id)).encode().toUri();
		ResponseEntity<EntityModel<Quiz>> quizResponse = template.exchange(quizURI.resolve(quizURI), HttpMethod.GET,
				null, new ParameterizedTypeReference<EntityModel<Quiz>>() {
				});
		Quiz theQuiz = quizResponse.getBody().getContent();
		return theQuiz;
	}

}
