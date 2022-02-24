package com.fa.training.group01.dao.impl;

import org.springframework.stereotype.Component;

import com.fa.training.group01.dao.IAnswerTakenDAO;
import com.fa.training.group01.domain_model.AnswerTaken;
import com.fa.training.group01.domain_model.GenericModel;

@Component
public class AnswerTakenDAO extends AbstractDAO<AnswerTaken, GenericModel> implements IAnswerTakenDAO {
}
