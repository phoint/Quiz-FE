package com.fa.training.group01.controller.admin;

import java.io.IOException;
import java.util.List;

import com.fa.training.group01.domain_model.Ex.QuizEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fa.training.group01.domain_model.Part;
import com.fa.training.group01.domain_model.Quiz;
import com.fa.training.group01.domain_model.QuizCreator;
import com.fa.training.group01.model.CurrentUser;
import com.fa.training.group01.security.CustomUserDetails;
import com.fa.training.group01.service.IPartService;
import com.fa.training.group01.service.IQuizCreatorService;
import com.fa.training.group01.service.IQuizService;

@Controller
@RequestMapping(value = "/admin")
public class QuizController {
	@Autowired
	@Qualifier("hypermediaRestTemplate")
	private RestTemplate template;

	@Autowired
	private IQuizService quizService;
	@Autowired
	private IPartService partService;

	@Autowired
	private IQuizCreatorService quizCreatorService;

	@GetMapping(value = "/quizzes")
	public String showQuizPage(Model theModel, Quiz quiz) {
		List<Quiz> quizList = quizService.findAll();
		System.out.println(quizList.size());
		theModel.addAttribute("quizzes", quizList);
		return "admin/quizzes";
	}

	@PostMapping(value = "/quizzes")
	public String createQuiz(Quiz quiz, BindingResult bindingResult,
			ModelMap model, RedirectAttributes rdrAttributes,@CurrentUser CustomUserDetails userDetail) {
		if (bindingResult.hasErrors()) {
			return "admin/quizzes";
		}
		QuizCreator quizCreator = quizCreatorService.findById(userDetail.getId());
		Quiz quizPosted = quizService.save(quiz);
		
		quizCreator.getQuizzes().add(quizPosted);
		quizCreatorService.addQuiz(quizCreator);
		model.clear();
		rdrAttributes.addAttribute("id", quizPosted.getId());
		return "redirect:/admin/new-quiz";
	}

	@GetMapping(value = { "/new-quiz", "/edit-quiz" }, params = { "id" })
	public String showCreateQuizPage(@RequestParam("id") int id, ModelMap model) {
		Quiz theQuiz = quizService.findById(id);
		model.addAttribute("quiz", theQuiz);
		System.out.println(theQuiz);
		return "admin/parts-management";
	}

	@PostMapping(value = { "/new-quiz", "/edit-quiz" }, params = { "id" })
	public String saveQuiz(@ModelAttribute("quiz") Quiz quiz, ModelMap model) {
		quizService.update(quiz);
		partService.updateAll(quiz.getParts());
		return "admin/parts-management";
	}

	@RequestMapping(value = { "/new-quiz", "/edit-quiz" }, params = { "addPart", "id" })
	public String addPart(@ModelAttribute("quiz") Quiz quiz, BindingResult bindingResult,
			@RequestParam("id") int quizId) {
		Part newPart = partService.save(new Part());

		quiz.getParts().add(newPart);
		quizService.addPart(quiz);
		System.out.println(quiz);
		return "admin/parts-management";
	}

	@RequestMapping(value = { "/new-quiz", "/edit-quiz" }, params = { "removePart", "id" })
	public String removePart(@ModelAttribute("quiz") Quiz quiz, BindingResult bindingResult,
			@RequestParam("removePart") int partIndex) {
		quiz.getParts().remove(partIndex);
		quizService.addPart(quiz);
		System.out.println(quiz);
		return "admin/parts-management";
	}

//	@GetMapping(value="/new-quiz")
//	public String showPartManagementForm() {
//		return "redirect:/admin/quizzes";
//	}

	@GetMapping({ "/exImp" })
	public String exImp(Model model) {
		return "admin/excel-admin";
	}

	@PostMapping("/import")
	@ResponseBody
	public QuizEx mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile, Model model) throws IOException {

		QuizEx quizEx = quizService.getData(reapExcelDataFile);
		return quizEx;
	}
}
