package com.fa.training.group01.service;

import java.util.List;

import com.fa.training.group01.domain_model.Topic;

public interface ITopicService {
	Topic findById(int topicId);
	
	List<Topic> findAll();
	
	void addQuiz(Topic topic);
}
