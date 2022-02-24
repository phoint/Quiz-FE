package com.fa.training.group01.dao.impl;

import org.springframework.stereotype.Component;

import com.fa.training.group01.dao.IQuizTakerDAO;
import com.fa.training.group01.domain_model.QuizTaken;
import com.fa.training.group01.domain_model.QuizTaker;

@Component
public class QuizTakerDAO extends AbstractDAO<QuizTaker, QuizTaken> implements IQuizTakerDAO {

}
