package com.fa.training.group01.dao.impl;

import org.springframework.stereotype.Component;

import com.fa.training.group01.dao.IPartDAO;
import com.fa.training.group01.domain_model.Part;
import com.fa.training.group01.domain_model.Section;

@Component
public class PartDAO extends AbstractDAO<Part, Section> implements IPartDAO {

//	@Autowired
//	@Qualifier(value = "hypermediaRestTemplate")
//	private RestTemplate template;
//
//	private HttpHeaders header;
//
//	public PartDAO() {
//		header = new HttpHeaders();
//	}
//
//	@Override
//	public Part save(Part part) {
//		EntityModel<Part> partPosting = EntityModel.of(part);
//		HttpEntity<EntityModel<Part>> request = new HttpEntity<EntityModel<Part>>(
//				partPosting);
//		ResponseEntity<EntityModel<Part>> resObject = template.exchange(
//				API.HOST + API.PART_MODULE, HttpMethod.POST,
//				request, new ParameterizedTypeReference<EntityModel<Part>>() {
//				});
//		return resObject.getBody().getContent();
//	}
//
//	@Override
//	public void addSection(Part part) {
//		Link partURI = Link.of(API.HOST + API.Part.SECTION);
//		Link sectionURI = Link.of(API.HOST + API.Section.SECTION);
//
//		StringBuilder sectionURIList = new StringBuilder();
//
//		for (Section section : part.getSections()) {
//			sectionURIList.append(sectionURI.expand(section.getId()).getHref())
//					.append("\n");
//		}
//
//		header.add("Content-type", "text/uri-list");
//
//		HttpEntity<String> request = new HttpEntity<String>(
//				sectionURIList.toString(), header);
//
//		template.exchange(partURI.expand(part.getId()).getHref(),
//				HttpMethod.PUT, request, String.class);
//
//		header.remove("Content-type");
//
//	}
//
//	@Override
//	public Part update(Part part) {
//		Link partURI = Link.of(API.HOST + API.Part.PART);
//		HttpEntity<Part> request = new HttpEntity<Part>(part, header);
//		ResponseEntity<EntityModel<Part>> responseEntity = template.exchange(
//				partURI.getHref(), HttpMethod.PUT, request,
//				new ParameterizedTypeReference<EntityModel<Part>>() {
//				}, part.getId());
//
//		return responseEntity.getBody().getContent();
//	}
//
//	@Override
//	public List<Part> findAllByQuiz(int quizId) {
//		Link partURI = Link.of(API.HOST + API.Quiz.PART);
//		ResponseEntity<CollectionModel<Part>> responseEntity = template
//				.exchange(
//						partURI.getHref(), HttpMethod.GET, null,
//						new ParameterizedTypeReference<CollectionModel<Part>>() {
//						}, quizId);
//		List<Part> parts = new ArrayList<Part>(
//				responseEntity.getBody().getContent());
//		return parts;
//	}
//
//	@Override
//	public Part findById(int partId) {
//		Link partURI = Link.of(API.HOST + API.Part.PART);
//		ResponseEntity<EntityModel<Part>> partResource = template.exchange(
//				partURI.getHref(), HttpMethod.GET, null,
//				new ParameterizedTypeReference<EntityModel<Part>>() {
//				}, partId);
//
//		Part part = partResource.getBody().getContent();
//		System.out.println(part);
//		return part;
//	}
}
