package com.fa.training.group01.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.fa.training.group01.domain_model.Quiz;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	@Qualifier("hypermediaRestTemplate")
	private RestTemplate template;
	
	@RequestMapping(value = "/login",method =RequestMethod.GET )
	public String logPage() {
		return "admin/login-admin";
	}
	
	@GetMapping({"","/start"})
	public String startPage() {
//		ResponseEntity<EntityModel<Quiz>> response = template.exchange("http://localhost:9090/quizzes/1", HttpMethod.GET, null, new ParameterizedTypeReference<EntityModel<Quiz>>() {});		
//		EntityModel<Quiz> quiz = response.getBody();
//		System.out.println(quiz.getLink(LinkRelation.of("self")));
//		System.out.println(quiz.getContent());
		return "admin/dashboard";
	}
	
//	@RequestMapping(value = "/user",method =RequestMethod.GET )
//	public String userPage() {
//		return "admin/user-admin";
//	}
	
	@RequestMapping(value = "/quiz",method =RequestMethod.GET )
	public String quizPage() {
		return "admin/quiz-admin";
	}
	
	@RequestMapping(value = "/listQ",method =RequestMethod.GET )
	public String listPage() {
		return "admin/list-question";
	}
	
	@GetMapping(value = "/createQ")
	public String createPage() {
		return "admin/create-question";
	}
	
//	@PostMapping(value="/new-quiz")
//	public String createQuiz() {
//		return "redirect:admin/createQ";
//	}
}
