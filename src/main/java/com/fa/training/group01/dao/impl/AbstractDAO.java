package com.fa.training.group01.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.core.TypeReferences.EntityModelType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fa.training.group01.dao.GenericDAO;
import com.fa.training.group01.domain_model.GenericModel;
import com.fa.training.group01.domain_model.Quiz;
import com.fa.training.group01.util.API;

public class AbstractDAO<T extends GenericModel, G extends GenericModel> implements GenericDAO<T, G> {

	@Autowired
	@Qualifier(value = "hypermediaRestTemplate")
	private RestTemplate template;

	@Autowired
	private HttpHeaders header;

	@Override
	public T save(T object, String apiModule, ParameterizedTypeReference<EntityModel<T>> resType) {
		Link uri = Link.of(API.HOST + apiModule);
		if (object.getId() != null) {
			object.setId(null);
		}
		HttpEntity<EntityModel<T>> request = new HttpEntity<>(EntityModel.of(object));
		ResponseEntity<EntityModel<T>> resObject = template.exchange(uri.getHref(), HttpMethod.POST, request, resType);
		return resObject.getBody().getContent();
	}

	@Override
	public T update(T object, String apiModule, ParameterizedTypeReference<EntityModel<T>> resType) {
		Link uri = Link.of(API.HOST + apiModule);

		HttpEntity<EntityModel<T>> request = new HttpEntity<>(EntityModel.of(object), header);

		ResponseEntity<EntityModel<T>> exchange = template.exchange(uri.getHref(), HttpMethod.PUT, request, resType,
				object.getId());

		return exchange.getBody().getContent();
	}

	@Override
	public void addChildren(T object, List<G> childList, String apiParent, String apiChild) {
		Link uriParent = Link.of(API.HOST + apiParent);
		Link uriChild = Link.of(API.HOST + apiChild);

		StringBuilder uriChildList = new StringBuilder();

		for (G child : childList) {
			uriChildList.append(uriChild.expand(child.getId()).getHref()).append("\n");
		}

		header.add("Content-type", "text/uri-list");

		HttpEntity<String> request = new HttpEntity<String>(uriChildList.toString(), header);

		template.exchange(uriParent.expand(object.getId()).getHref(), HttpMethod.PUT, request, String.class);

		header.remove("Content-type");
	}

	@Override
	public void addChildren(T object, Integer[] childIdList, String apiParent, String apiChild) {
		Link uriParent = Link.of(API.HOST + apiParent);
		Link uriChild = Link.of(API.HOST + apiChild);

		StringBuilder uriChildList = new StringBuilder();

		for (int i = 0; i < childIdList.length; i++) {
			uriChildList.append(uriChild.expand(childIdList[i]).getHref()).append("\n");
		}

		header.add("Content-type", "text/uri-list");

		HttpEntity<String> request = new HttpEntity<String>(uriChildList.toString(), header);

		template.exchange(uriParent.expand(object.getId()).getHref(), HttpMethod.PUT, request, String.class);

		header.remove("Content-type");

	}

	@Override
	public void addChildren(int parentId, Integer[] childIdList, String apiParent, String apiChild) {
		Link uriParent = Link.of(API.HOST + apiParent);
		Link uriChild = Link.of(API.HOST + apiChild);

		StringBuilder uriChildList = new StringBuilder();

		for (int i = 0; i < childIdList.length; i++) {
			uriChildList.append(uriChild.expand(childIdList[i]).getHref()).append("\n");
		}

		header.add("Content-type", "text/uri-list");

		HttpEntity<String> request = new HttpEntity<String>(uriChildList.toString(), header);

		template.exchange(uriParent.expand(parentId).getHref(), HttpMethod.PUT, request, String.class);

		header.remove("Content-type");

	}

	@Override
	public <G extends GenericModel> void addChild(T parent, G child, String apiParent, String apiChild) {
		Link uriParent = Link.of(API.HOST + apiParent);
		Link uriChild = Link.of(API.HOST + apiChild);

		StringBuilder uriChildList = new StringBuilder(uriChild.expand(child.getId()).getHref());

		header.add("Content-type", "text/uri-list");

		HttpEntity<String> request = new HttpEntity<String>(uriChildList.toString(), header);

		template.exchange(uriParent.expand(parent.getId()).getHref(), HttpMethod.PUT, request, String.class);

		header.remove("Content-type");
	}

	@Override
	public void addChild(T parent, int childId, String apiParent, String apiChild) {
		Link uriParent = Link.of(API.HOST + apiParent);
		Link uriChild = Link.of(API.HOST + apiChild);

		StringBuilder uriChildList = new StringBuilder(uriChild.expand(childId).getHref());

		header.add("Content-type", "text/uri-list");

		HttpEntity<String> request = new HttpEntity<String>(uriChildList.toString(), header);

		template.exchange(uriParent.expand(parent.getId()).getHref(), HttpMethod.PUT, request, String.class);

		header.remove("Content-type");

	}

	@Override
	public void addChild(int parentId, int childId, String apiParent, String apiChild) {
		Link uriParent = Link.of(API.HOST + apiParent);
		Link uriChild = Link.of(API.HOST + apiChild);

		StringBuilder uriChildList = new StringBuilder(uriChild.expand(childId).getHref());

		header.add("Content-type", "text/uri-list");

		HttpEntity<String> request = new HttpEntity<String>(uriChildList.toString(), header);

		template.exchange(uriParent.expand(parentId).getHref(), HttpMethod.PUT, request, String.class);

		header.remove("Content-type");

	}

	@Override
	public <T> List<T> findAll(String apiModule, ParameterizedTypeReference<CollectionModel<T>> resType) {
		Link uri = Link.of(API.HOST + apiModule);
		ResponseEntity<CollectionModel<T>> exchange = template.exchange(uri.getHref(), HttpMethod.GET, null, resType);
		List<T> entities = new ArrayList<>(exchange.getBody().getContent());

		return entities;
	}

	@Override
	public T findById(int objectId, String apiModule, ParameterizedTypeReference<EntityModel<T>> resType) {
		Link uri = Link.of(API.HOST + apiModule);

		ResponseEntity<EntityModel<T>> exchange = template.exchange(uri.getHref(), HttpMethod.GET, null, resType,
				objectId);

		return exchange.getBody().getContent();
	}

	@Override
	public <T> List<T> findAllByParent(int parentId, String apiModule,
			ParameterizedTypeReference<CollectionModel<T>> resType) {
		Link uri = Link.of(API.HOST + apiModule);

		ResponseEntity<CollectionModel<T>> exchange = template.exchange(uri.getHref(), HttpMethod.GET, null, resType,
				parentId);

		List<T> objs = exchange.getBody().getContent().stream().collect(Collectors.toList());
		return objs;
	}

	@Override
	public T findParent(int childId, String apiModule, ParameterizedTypeReference<EntityModel<T>> resType) {
		Link uri = Link.of(API.HOST + apiModule);

		ResponseEntity<EntityModel<T>> exchange = template.exchange(uri.getHref(), HttpMethod.GET, null, resType,
				childId);
		return exchange.getStatusCode() == HttpStatus.NOT_FOUND ? null : exchange.getBody().getContent();
	}
}
