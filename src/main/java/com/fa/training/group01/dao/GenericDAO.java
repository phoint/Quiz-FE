package com.fa.training.group01.dao;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.core.TypeReferences.EntityModelType;

import com.fa.training.group01.domain_model.GenericModel;
import com.fa.training.group01.domain_model.Question;

public interface GenericDAO<T extends GenericModel,G extends GenericModel> {
	T save(T object, String apiModule, ParameterizedTypeReference<EntityModel<T>> resType);

	T update(T object, String apiModule, ParameterizedTypeReference<EntityModel<T>> resType);
	
	<T> List<T> findAll(String apiModule, ParameterizedTypeReference<CollectionModel<T>> resType);

	T findById(int objectId, String apiModule, ParameterizedTypeReference<EntityModel<T>> resType);

	<T> List<T> findAllByParent(int parentId, String apiModule, ParameterizedTypeReference<CollectionModel<T>> resType);
	
	<G extends GenericModel> void addChild(T object, List<G> childList, String apiParent, String apiChild);
	
}
