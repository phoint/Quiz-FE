package com.fa.training.group01.dao.impl;

import org.springframework.stereotype.Component;

import com.fa.training.group01.dao.IQuizCreatorDAO;
import com.fa.training.group01.domain_model.Quiz;
import com.fa.training.group01.domain_model.QuizCreator;

@Component
public class QuizCreatorDAO extends AbstractDAO<QuizCreator, Quiz> implements IQuizCreatorDAO {

}
