package com.fa.training.group01.dao.impl;

import org.springframework.stereotype.Component;

import com.fa.training.group01.dao.ITopicDAO;
import com.fa.training.group01.domain_model.Quiz;
import com.fa.training.group01.domain_model.Topic;

import groovyjarjarpicocli.CommandLine.Command;

@Component
public class TopicDAO extends AbstractDAO<Topic, Quiz> implements ITopicDAO {

}
