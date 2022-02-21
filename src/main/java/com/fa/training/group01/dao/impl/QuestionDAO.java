package com.fa.training.group01.dao.impl;

import org.springframework.stereotype.Component;

import com.fa.training.group01.dao.IQuestionDAO;
import com.fa.training.group01.domain_model.Answer;
import com.fa.training.group01.domain_model.Question;

@Component
public class QuestionDAO extends AbstractDAO<Question, Answer> implements IQuestionDAO {

//	@Autowired
//	@Qualifier(value = "hypermediaRestTemplate")
//	private RestTemplate template;

//	@Override
//	public Question save(Question question) {
//		Link questionURI = Link.of(API.HOST + API.QUESTION_MODULE);
//		EntityModel<Question> questionPosting = EntityModel.of(question);
//		HttpEntity<EntityModel<Question>> request = new HttpEntity<EntityModel<Question>>(
//				questionPosting);
//		ResponseEntity<EntityModel<Question>> resObject = template.exchange(
//				questionURI.getHref(), HttpMethod.POST, request,
//				new ParameterizedTypeReference<EntityModel<Question>>() {
//				});
//		return resObject.getBody().getContent();
//	}
//
//	@Override
//	public Question update(Question question) {
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
//	public Question findById(int questionId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Question> findAllBySection(int sectionId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Question addAnswer(Question question) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
