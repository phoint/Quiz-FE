package com.fa.training.group01.controller.student;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fa.training.group01.domain_model.AnswerTaken;
import com.fa.training.group01.domain_model.Quiz;
import com.fa.training.group01.domain_model.QuizTaken;
import com.fa.training.group01.model.QuizDone;
import com.fa.training.group01.model.UserChoice;
import com.fa.training.group01.service.IAnswerTakenService;
import com.fa.training.group01.service.IQuestionService;
import com.fa.training.group01.service.IQuizService;
import com.fa.training.group01.service.IQuizTakenService;

@Controller
@RequestMapping(value = "quiz")
public class QuizTakenController {

	@Autowired
	IQuizService quizService;

	@Autowired
	IQuestionService questionService;

	@Autowired
	IQuizTakenService quizTakenService;

	@Autowired
	IAnswerTakenService answerTakenService;

	@GetMapping(value = "do-quiz", params = { "id" })
	public String getQuizPage(@RequestParam("id") int quizId, ModelMap model) {
		Quiz theQuiz = quizService.findFullQuiz(quizId);
		QuizDone quizDone = new QuizDone();
		quizDone.createHolder(quizService.countQuestion(quizId));

		model.addAttribute("quiz", theQuiz);
		model.addAttribute("quizDone", quizDone);

		return "student/take-quiz";
	}

	@PostMapping(value = "do-quiz", params = { "id" })
	public String submitQuiz(@ModelAttribute("quizDone") QuizDone quizDone, @RequestParam("id") int quizId,
			RedirectAttributes rdrAttr) {
		System.out.println("Quiz Done: " + quizDone);
		QuizTaken quizTaken = quizTakenService.save(new QuizTaken());

		List<AnswerTaken> answerTakens = new ArrayList<AnswerTaken>();

		for (UserChoice userChoice : quizDone.getChoices()) {
			AnswerTaken answerTaken = new AnswerTaken();
			String answerChosen = userChoice.getAnswerIds();
			if (answerChosen != null) {
				String[] answers = answerChosen.split(",");
				for (int j = 0; j < answers.length; j++) {
					answerTaken = answerTakenService.save(new AnswerTaken());
					answerTakenService.addInfo(answerTaken, userChoice.getQuestionId(), Integer.valueOf(answers[j]));
					answerTakens.add(answerTaken);
				}
			} else {
				answerTaken = answerTakenService.save(new AnswerTaken());
				answerTakenService.addInfo(answerTaken, userChoice.getQuestionId(), null);
				answerTakens.add(answerTaken);
				
			}
			System.out.println(answerTaken);
		}

		quizTaken.setAnswerTaken(answerTakens);
		quizTakenService.addAnswerTaken(quizTaken);
		quizTakenService.addQuiz(quizTaken, quizId);

		rdrAttr.addAttribute("taken", quizTaken.getId()).addFlashAttribute("id", quizId);
		return "redirect:/quiz/result";
	}

	@RequestMapping(value = "result", params = { "taken"})
	public String showResult(@RequestParam("taken") int takenId, ModelMap model) {
//		Quiz quiz = quizService.findFullQuiz(quizId);
//		QuizTaken quizTaken = quizTakenService.findById(takenId);
		Quiz quiz = quizService.findFullQuiz(2);
		QuizTaken quizTaken = quizTakenService.findById(31);
		quizTaken = quizTakenService.calculateScore(quizTaken);

		model.addAttribute("quizTaken", quizTaken).addAttribute("quiz", quiz);
		return "/student/quiz-result";
	}
}
