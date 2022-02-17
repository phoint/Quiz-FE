package com.fa.training.group01.controller.admin;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fa.training.group01.domain_model.Part;
import com.fa.training.group01.domain_model.Quiz;
import com.fa.training.group01.service.impl.QuizService;

@Controller
@RequestMapping(value = "/admin")
public class QuizController {
	@Autowired
	@Qualifier("hypermediaRestTemplate")
	private RestTemplate template;

	@Autowired
	private QuizService quizService;

	@GetMapping(value = "/quizzes")
	public String showQuizPage(Model theModel, Quiz quiz) {
		List<Quiz> quizList = quizService.findAll();
		System.out.println(quizList.size());
		theModel.addAttribute("quizzes", quizList);
		return "admin/quizzes";
	}

	@PostMapping(value = "/quizzes")
	public String createQuiz(Quiz quiz, BindingResult bindingResult,
			ModelMap model, RedirectAttributes rdrAttributes) {
		if (bindingResult.hasErrors()) {
			return "admin/quizzes";
		}

		Quiz quizPosted = quizService.save(quiz);
		model.clear();
		rdrAttributes.addAttribute("id", quizPosted.getId());
		return "redirect:/admin/new-quiz";
	}

	@GetMapping(value = "/new-quiz", params = { "id" })
	public String showCreateQuizPage(@RequestParam("id") int id,
			ModelMap model) {
		Quiz theQuiz = quizService.findById(id);
		model.addAttribute("quiz", theQuiz);
		System.out.println(theQuiz);
		return "admin/parts-management";
	}

	@RequestMapping(value = "/new-quiz", params = { "addPart", "id" })
	public String addPart(@ModelAttribute("quiz") Quiz quiz,
			BindingResult bindingResult) {
		quiz.getParts().add(new Part());
		System.out.println(quiz);
		return "admin/parts-management";
	}

	@RequestMapping(value = "/new-quiz", params = { "removePart", "id" })
	public String removePart(@ModelAttribute("quiz") Quiz quiz,
			BindingResult bindingResult,
			@RequestParam("removePart") int partIndex) {
		quiz.getParts().remove(partIndex);
		System.out.println(quiz);
		return "admin/parts-management";
	}

//	@GetMapping(value="/new-quiz")
//	public String showPartManagementForm() {
//		return "redirect:/admin/quizzes";
//	}
}
