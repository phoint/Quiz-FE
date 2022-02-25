package com.fa.training.group01.service;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import com.fa.training.group01.domain_model.Excel;
import com.fa.training.group01.domain_model.Quiz;
import org.springframework.web.multipart.MultipartFile;

public interface IQuizService {
	Quiz save(Quiz quiz);
	
	void addPart(Quiz quiz);
	
	Quiz update(Quiz quiz);

	List<Quiz> findAll();

	Quiz findById(int id);

	List<Excel> getData(MultipartFile reapExcelDataFile) throws IOException;
}
