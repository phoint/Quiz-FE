package com.fa.training.group01.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fa.training.group01.domain_model.Excel;
import com.fa.training.group01.util.Exmapper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import com.fa.training.group01.dao.IPartDAO;
import com.fa.training.group01.dao.IQuizDAO;
import com.fa.training.group01.domain_model.Part;
import com.fa.training.group01.domain_model.Quiz;
import com.fa.training.group01.service.IQuizService;
import com.fa.training.group01.util.API;
import org.springframework.web.multipart.MultipartFile;

@Service
public class QuizService implements IQuizService {

	@Autowired
	IQuizDAO quizDAO;

	@Autowired
	IPartDAO partDAO;

	@Override
	public Quiz save(Quiz quiz) {
		return quizDAO.save(quiz,API.QUIZ_MODULE,new ParameterizedTypeReference<EntityModel<Quiz>>() {
		});
	}

	@Override
	public void addPart(Quiz quiz) {
		quizDAO.addChild(quiz, quiz.getParts(), API.Quiz.PART, API.Part.PART);
	}

	@Override
	public Quiz update(Quiz quiz) {
		return quizDAO.update(quiz, API.Quiz.QUIZ, new ParameterizedTypeReference<EntityModel<Quiz>>() {
		});
	}

	@Override
	public List<Quiz> findAll() {
		return quizDAO.findAll(API.QUIZ_MODULE, new ParameterizedTypeReference<CollectionModel<Quiz>>() {
		});
	}

	@Override
	public Quiz findById(int id) {
		Quiz quiz = quizDAO.findById(id, API.Quiz.QUIZ, new ParameterizedTypeReference<EntityModel<Quiz>>() {
		});
		List<Part> parts = partDAO.findAllByParent(id, API.Quiz.PART, new ParameterizedTypeReference<CollectionModel<Part>>() {
		});

		if (parts != null) {
			quiz.setParts(parts);
		}

		return quiz;
	}

	@Override
	public List<Excel> getData(MultipartFile reapExcelDataFile) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		List<Excel> list = new ArrayList<Excel>();
		for(int i = 1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
			XSSFRow row = worksheet.getRow(i);
			Excel ex = Exmapper.mapEx(new Excel(),row);
			list.add(ex);
		}
		return  list;
	}


}
