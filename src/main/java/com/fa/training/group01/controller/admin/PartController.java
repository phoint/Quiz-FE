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
public class PartController {

	@Autowired
	IPartService partService;

	@Autowired
	ISectionService sectionService;

	@Autowired
	IQuestionService questionService;
	
	@Autowired
	IAnswerService answerService;
	

	@GetMapping(value = "/edit-part", params = { "id" })
	public String editPart(@RequestParam("id") int id, ModelMap model) {

		Part thePart = partService.findById(id);
		model.addAttribute("part", thePart);
		model.addAttribute("question", questionService.createBlank());

		return "admin/edit-parts";
	}

	@PostMapping(value = "/edit-part", params = { "id" })
	public String savePart(@ModelAttribute("part") Part part, @RequestParam("id") int partId, ModelMap model) {
		sectionService.updateAll(part.getSections());
		partService.update(part);
		model.addAttribute("part", partService.findById(partId));
		model.addAttribute("question", questionService.createBlank());
		return "admin/edit-parts";
	}

	@RequestMapping(value = "/edit-part", params = { "id", "addSection" })
	public String addSection(@ModelAttribute("part") Part part,
			BindingResult bindingResult, ModelMap model) {
		Section newSection = sectionService.save(new Section());
		System.out.println(newSection);
		part.getSections().add(newSection);
		partService.addSection(part);
		
		model.addAttribute("part", partService.findById(part.getId()));
		model.addAttribute("question", questionService.createBlank());
		return "admin/edit-parts";
	}

	@RequestMapping(value = "/edit-part", params = { "id",
			"removeSection" })
	public String removeSection(@ModelAttribute("part") Part part,
			BindingResult bindingResult, ModelMap model,
			@RequestParam("removeSection") int sectionIndex) {
		part.getSections().remove(sectionIndex);
		partService.addSection(part);
		System.out.println(part);
		
		model.addAttribute("part", partService.findById(part.getId()));
		model.addAttribute("question", questionService.createBlank());
		return "admin/edit-parts";
	}

	@RequestMapping(value = "/edit-part", params = { "id", "addQuestion" })
	public String addQuestion(@ModelAttribute("question") Question question,
			BindingResult bindingResult, @RequestParam("id") int partId,
			RedirectAttributes rdrAttr, ModelMap model) {
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
}
