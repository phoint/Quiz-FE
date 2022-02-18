package com.fa.training.group01.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fa.training.group01.dao.IPartDAO;
import com.fa.training.group01.domain_model.Part;
import com.fa.training.group01.domain_model.Quiz;
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
	public Part update(Part part) {
		Link partURI = Link.of(API.HOST + API.Part.PART);
		HttpEntity<Part> request = new HttpEntity<Part>(part, header);
		ResponseEntity<EntityModel<Part>> responseEntity = template.exchange(
				partURI.getHref(), HttpMethod.PUT, request,
				new ParameterizedTypeReference<EntityModel<Part>>() {
				}, part.getId());

		return responseEntity.getBody().getContent();
	}

}
