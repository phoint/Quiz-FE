package com.fa.training.group01.service;

import java.io.IOException;
import java.util.List;

import com.fa.training.group01.domain_model.Ex.QuizEx;
import com.fa.training.group01.domain_model.Quiz;
import org.springframework.web.multipart.MultipartFile;

public interface IQuizService {
	Quiz save(Quiz quiz);
	
	void addPart(Quiz quiz);
	
	Quiz update(Quiz quiz);

	List<Quiz> findAll();

	Quiz findById(int id);
	
	Quiz findFullQuiz(int id);
	
	int countQuestion(int quizId); 

	QuizEx getData(MultipartFile reapExcelDataFile) throws IOException;
}
