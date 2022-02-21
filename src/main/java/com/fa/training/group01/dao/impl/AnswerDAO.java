package com.fa.training.group01.dao.impl;

import org.springframework.stereotype.Component;

import com.fa.training.group01.dao.IAnswerDAO;
import com.fa.training.group01.domain_model.Answer;
import com.fa.training.group01.domain_model.GenericModel;

@Component
public class AnswerDAO extends AbstractDAO<Answer, GenericModel>
		implements IAnswerDAO {

}
