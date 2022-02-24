package com.fa.training.group01.dao.impl;

import org.springframework.stereotype.Component;

import com.fa.training.group01.dao.IQuizTakenDAO;
import com.fa.training.group01.domain_model.AnswerTaken;
import com.fa.training.group01.domain_model.GenericModel;
import com.fa.training.group01.domain_model.QuizTaken;

@Component
public class QuizTakenDAO extends AbstractDAO<QuizTaken, AnswerTaken> implements IQuizTakenDAO {
}
