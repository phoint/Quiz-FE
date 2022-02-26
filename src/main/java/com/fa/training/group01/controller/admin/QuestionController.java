package com.fa.training.group01.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fa.training.group01.domain_model.Answer;
import com.fa.training.group01.domain_model.Part;
import com.fa.training.group01.domain_model.Question;
import com.fa.training.group01.domain_model.Section;
import com.fa.training.group01.service.IAnswerService;
import com.fa.training.group01.service.IPartService;
import com.fa.training.group01.service.IQuestionService;
import com.fa.training.group01.service.ISectionService;

@Controller
@RequestMapping(value = "/admin")
public class QuestionController {

	@Autowired
	IPartService partService;

	@Autowired
	ISectionService sectionService;

	@Autowired
	IQuestionService questionService;

	@Autowired
	IAnswerService answerService;

	@RequestMapping(value = "/new-question", params = { "addQuestion" })
	public String addQuestion(@ModelAttribute("question") Question question, BindingResult bindingResult,
			@RequestParam("partId") int partId, RedirectAttributes rdrAttr, ModelMap model) {
		System.out.println(question);
		System.out.println(question.getId());
		System.out.println(question.getSectionId());
		Section section = sectionService.findById(question.getSectionId());
		Question newQuestion = questionService.save(question);

		question.getAnswers().get(question.getAnswerIndex()).setCorrect(true);
		for (Answer answer : question.getAnswers()) {
			newQuestion.getAnswers().add(answerService.save(answer));
		}
		questionService.addAnswer(newQuestion);

		System.out.println(section);
		section.getQuestions().add(newQuestion);
		System.out.println(section);
		sectionService.addQuestion(section);

		System.out.println(newQuestion);
		System.out.println(newQuestion.getId());
		rdrAttr.addAttribute("id", partId);
		return "redirect:/admin/edit-part";
	}

	@PostMapping(value = "/edit-question")
	public String editQuestion(@ModelAttribute("question") Question question, BindingResult bindingResult,
			@RequestParam("partId") int partId, RedirectAttributes rdrAttr, ModelMap model) {
		Question newQuestion = questionService.update(question);

		for (Answer answer : question.getAnswers()) {
			answer.setCorrect(false);
		}
		question.getAnswers().get(question.getAnswerIndex()).setCorrect(true);
		for (Answer answer : question.getAnswers()) {
			answerService.update(answer);
		}

		System.out.println(newQuestion);
		System.out.println(newQuestion.getId());
		model.clear();
		rdrAttr.addAttribute("id", partId);
		return "redirect:/admin/edit-part";
	}
}
