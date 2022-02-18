package com.fa.training.group01.dao.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fa.training.group01.dao.ISectionDAO;
import com.fa.training.group01.domain_model.Part;
import com.fa.training.group01.domain_model.Quiz;
import com.fa.training.group01.domain_model.Section;
import com.fa.training.group01.util.API;

@Component
public class SectionDAO implements ISectionDAO {

	@Autowired
	@Qualifier(value = "hypermediaRestTemplate")
	private RestTemplate template;

	private Traverson traverson;
	private HttpHeaders header;

	public SectionDAO() {
		header = new HttpHeaders();
	}

	@Override
	public Section save(Section theSection) {
		URI saveApi = URI.create(API.HOST + API.SECTION_MODULE);
		HttpEntity<EntityModel<Section>> request = new HttpEntity<EntityModel<Section>>(
				EntityModel.of(theSection));
		ResponseEntity<EntityModel<Section>> resObject = template.exchange(
				saveApi, HttpMethod.POST, request,
				new ParameterizedTypeReference<EntityModel<Section>>() {
				});
		return resObject.getBody().getContent();
	}
	
	@Override
	public List<Section> findAllByPart(int partId) {
		Link sectionURI = Link.of(API.HOST + API.Part.SECTION);
		ResponseEntity<CollectionModel<Section>> responseEntity = template
				.exchange(
						sectionURI.getHref(), HttpMethod.GET, null,
						new ParameterizedTypeReference<CollectionModel<Section>>() {
						}, partId);
		List<Section> sections = new ArrayList<>(
				responseEntity.getBody().getContent());
		return sections;
	}

}
