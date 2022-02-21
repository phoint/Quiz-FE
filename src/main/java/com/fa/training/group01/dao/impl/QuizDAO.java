package com.fa.training.group01.dao.impl;

import org.springframework.stereotype.Component;

import com.fa.training.group01.dao.IQuizDAO;
import com.fa.training.group01.domain_model.Part;
import com.fa.training.group01.domain_model.Quiz;

@Component
public class QuizDAO extends AbstractDAO<Quiz, Part> implements IQuizDAO {

//	@Autowired
//	@Qualifier(value = "hypermediaRestTemplate")
//	private RestTemplate template;
//
//	private Traverson traverson;
//	private HttpHeaders header;
//
//	public QuizDAO() {
//		header = new HttpHeaders();
//	}
//
//	@Override
//	public Quiz save(Quiz quiz) {
//		EntityModel<Quiz> quizPosting = EntityModel.of(quiz);
//		HttpEntity<EntityModel<Quiz>> request = new HttpEntity<EntityModel<Quiz>>(
//				quizPosting);
//		ResponseEntity<EntityModel<Quiz>> resObject = template.exchange(
//				API.HOST + API.QUIZ_MODULE, HttpMethod.POST,
//				request, new ParameterizedTypeReference<EntityModel<Quiz>>() {
//				});
//		return resObject.getBody().getContent();
//	}
//
//	@Override
//	public void addPart(Quiz quiz) {
//		Link partURI = Link.of(API.HOST + API.Part.PART);
//		Link quizURI = Link.of(API.HOST + API.Quiz.PART);
//
//		StringBuilder partURIList = new StringBuilder();
//
//		for (Part part : quiz.getParts()) {
//			partURIList.append(partURI.expand(part.getId()).getHref())
//					.append("\n");
//		}
//
//		header.add("Content-type", "text/uri-list");
//
//		HttpEntity<String> request = new HttpEntity<String>(
//				partURIList.toString(), header);
//
//		template.exchange(quizURI.expand(quiz.getId()).getHref(),
//				HttpMethod.PUT, request, String.class);
//		
//		header.remove("Content-type");
//	}
//
//	@Override
//	public Quiz update(Quiz quiz) {
//		Link quizURI = Link.of(API.HOST + API.Quiz.QUIZ);
//		HttpEntity<Quiz> request = new HttpEntity<Quiz>(quiz, header);
//		ResponseEntity<EntityModel<Quiz>> responseEntity = template.exchange(
//				quizURI.getHref(), HttpMethod.PUT, request,
//				new ParameterizedTypeReference<EntityModel<Quiz>>() {
//				}, quiz.getId());
//
//		return responseEntity.getBody().getContent();
//	}
//
//	@Override
//	public List<Quiz> findAll() {
//		ResponseEntity<CollectionModel<Quiz>> quizResponse = template.exchange(
//				API.HOST + API.QUIZ_MODULE,
//				HttpMethod.GET, null,
//				new ParameterizedTypeReference<CollectionModel<Quiz>>() {
//				});
//		List<Quiz> quizzes = new ArrayList<Quiz>(
//				quizResponse.getBody().getContent());
//
//		return quizzes;
//	}
//
//	@Override
//	public Quiz findById(int id) {
//		UriComponents uriComponents = UriComponentsBuilder
//				.fromUriString(API.HOST).path(API.QUIZ_MODULE).path("/{id}")
//				.build();
//		URI quizURI = uriComponents.expand(Integer.toString(id)).encode()
//				.toUri();
//		ResponseEntity<EntityModel<Quiz>> quizResponse = template.exchange(
//				quizURI.resolve(quizURI), HttpMethod.GET,
//				null, new ParameterizedTypeReference<EntityModel<Quiz>>() {
//				});
//		Quiz theQuiz = quizResponse.getBody().getContent();
//		return theQuiz;
//	}
}
