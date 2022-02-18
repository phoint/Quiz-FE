package com.fa.training.group01.dao.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fa.training.group01.dao.IPartDAO;
import com.fa.training.group01.domain_model.Part;
import com.fa.training.group01.domain_model.Section;
import com.fa.training.group01.util.API;

@Component
public class PartDAO implements IPartDAO {

	@Autowired
	@Qualifier(value = "hypermediaRestTemplate")
	private RestTemplate template;

	private HttpHeaders header;

	public PartDAO() {
		header = new HttpHeaders();
	}

	@Override
	public Part save(Part part) {
		EntityModel<Part> partPosting = EntityModel.of(part);
		HttpEntity<EntityModel<Part>> request = new HttpEntity<EntityModel<Part>>(
				partPosting);
		ResponseEntity<EntityModel<Part>> resObject = template.exchange(
				API.HOST + API.PART_MODULE, HttpMethod.POST,
				request, new ParameterizedTypeReference<EntityModel<Part>>() {
				});
		return resObject.getBody().getContent();
	}

	@Override
	public void addSection(Part part) {
		Link partURI = Link.of(API.HOST + API.Part.SECTION);
		Link sectionURI = Link.of(API.HOST + API.Section.SECTION);

		StringBuilder sectionURIList = new StringBuilder();

		for (Section section : part.getSections()) {
			sectionURIList.append(sectionURI.expand(section.getId()).getHref())
					.append("\n");
		}

		header.add("Content-type", "text/uri-list");

		HttpEntity<String> request = new HttpEntity<String>(
				sectionURIList.toString(), header);

		template.exchange(partURI.expand(part.getId()).getHref(),
				HttpMethod.PUT, request, String.class);

		header.remove("Content-type");

	}

	@Override
	public Part update(Part part) {
		Link partURI = Link.of(API.HOST + API.Part.PART);
		HttpEntity<Part> request = new HttpEntity<Part>(part, header);
		ResponseEntity<EntityModel<Part>> responseEntity = template.exchange(
				partURI.getHref(), HttpMethod.PUT, request,
				new ParameterizedTypeReference<EntityModel<Part>>() {
				}, part.getId());

		return responseEntity.getBody().getContent();
	}

	@Override
	public List<Part> findAllByQuiz(int quizId) {
		Link partURI = Link.of(API.HOST + API.Quiz.PART);
		ResponseEntity<CollectionModel<Part>> responseEntity = template
				.exchange(
						partURI.getHref(), HttpMethod.GET, null,
						new ParameterizedTypeReference<CollectionModel<Part>>() {
						}, quizId);
		List<Part> parts = new ArrayList<Part>(
				responseEntity.getBody().getContent());
		return parts;
	}

	@Override
	public Part findById(int partId) {
		Link partURI = Link.of(API.HOST + API.Part.PART);
		ResponseEntity<EntityModel<Part>> partResource = template.exchange(
				partURI.getHref(), HttpMethod.GET, null,
				new ParameterizedTypeReference<EntityModel<Part>>() {
				}, partId);

		Part part = partResource.getBody().getContent();
		System.out.println(part);
		return part;
	}
}
