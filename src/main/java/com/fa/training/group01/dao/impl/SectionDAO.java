package com.fa.training.group01.dao.impl;

import org.springframework.stereotype.Component;

import com.fa.training.group01.dao.ISectionDAO;
import com.fa.training.group01.domain_model.Question;
import com.fa.training.group01.domain_model.Section;

@Component
public class SectionDAO extends AbstractDAO<Section, Question> implements ISectionDAO {

//	@Override
//	public Section save(Section theSection) {
//		URI saveApi = URI.create(API.HOST + API.SECTION_MODULE);
//		HttpEntity<EntityModel<Section>> request = new HttpEntity<EntityModel<Section>>(
//				EntityModel.of(theSection));
//		ResponseEntity<EntityModel<Section>> resObject = template.exchange(
//				saveApi, HttpMethod.POST, request,
//				new ParameterizedTypeReference<EntityModel<Section>>() {
//				});
//		return resObject.getBody().getContent();
//	}
//
//	@Override
//	public void addQuestion(Section section) {
//		Link sectionURI = Link.of(API.HOST + API.Section.QUESTION);
//		Link questionURI = Link.of(API.HOST + API.Question.QUESTION);
//
//		StringBuilder questionURIList = new StringBuilder();
//
//		for (Question question : section.getQuestions()) {
//			questionURIList
//					.append(questionURI.expand(question.getId()).getHref())
//					.append("\n");
//		}
//
//		header.add("Content-type", "text/uri-list");
//
//		HttpEntity<String> request = new HttpEntity<String>(
//				questionURIList.toString(), header);
//
//		template.exchange(sectionURI.expand(section.getId()).getHref(),
//				HttpMethod.PUT, request, String.class);
//
//		header.remove("Content-type");
//	}
//
//	@Override
//	public List<Section> findAllByPart(int partId) {
//		Link sectionURI = Link.of(API.HOST + API.Part.SECTION);
//		ResponseEntity<CollectionModel<Section>> responseEntity = template
//				.exchange(
//						sectionURI.getHref(), HttpMethod.GET, null,
//						new ParameterizedTypeReference<CollectionModel<Section>>() {
//						}, partId);
//		List<Section> sections = new ArrayList<>(
//				responseEntity.getBody().getContent());
//		return sections;
//	}
//
//	@Override
//	public Section update(Section section) {
//		Link sectionURI = Link.of(API.HOST + API.Section.SECTION);
//		HttpEntity<Section> request = new HttpEntity<Section>(section, header);
//		ResponseEntity<EntityModel<Section>> responseEntity = template.exchange(
//				sectionURI.getHref(), HttpMethod.PUT, request,
//				new ParameterizedTypeReference<EntityModel<Section>>() {
//				}, section.getId());
//
//		return responseEntity.getBody().getContent();
//	}
//
//	@Override
//	public Section findById(int sectionId) {
//		Link sectionURI = Link.of(API.HOST + API.Section.SECTION);
//		ResponseEntity<EntityModel<Section>> responseEntity = template
//				.exchange(
//						sectionURI.getHref(), HttpMethod.GET, null,
//						new ParameterizedTypeReference<EntityModel<Section>>() {
//						}, sectionId);
//		return responseEntity.getBody().getContent();
//	}

}
