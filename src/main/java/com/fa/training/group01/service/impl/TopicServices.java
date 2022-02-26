package com.fa.training.group01.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import com.fa.training.group01.config.AppConfig;
import com.fa.training.group01.dao.IQuizDAO;
import com.fa.training.group01.dao.ITopicDAO;
import com.fa.training.group01.domain_model.Quiz;
import com.fa.training.group01.domain_model.Topic;
import com.fa.training.group01.service.ITopicService;
import com.fa.training.group01.util.API;

@Service
public class TopicServices<K,T> implements ITopicService {
    @Autowired
    AppConfig appConfig;
    
    @Autowired
    ITopicDAO topicDAO;
    
    @Autowired
    IQuizDAO quizDAO;

    public List<T> ListTemp(String s, Class<T[]> t){
        T[] entities = appConfig.restTemplate().getForObject(s,t);
        List<T> list = Arrays.asList(entities);
        return list;
    }

    public Boolean InsertTemp(String s, T t){
        Boolean b = appConfig.restTemplate().postForObject(s,t, Boolean.class);
        return b;
    }

    public T GetTempById(String s, Class<T> t,K id){
        T entity = appConfig.restTemplate().getForObject(s + id, t);
        return entity;
    }

    public Boolean UpdateTemp(String s, T t){
        try {
            appConfig.restTemplate().put(s, t);
            return true;
        }catch (Exception ex){
            System.err.println(ex);
            return false;
        }
    }

    public Boolean DeleteTemp(String s,K id){
        try {
            appConfig.restTemplate().delete(s + "?id=" + id);
            return true;
        }catch (Exception ex){
            System.err.println(ex);
            return false;
        }
    }
    
    @Override
    public Topic findById(int topicId) {
    	Topic theTopic = topicDAO.findById(topicId, API.Topic.TOPIC, new ParameterizedTypeReference<EntityModel<Topic>>() {
		});
    	List<Quiz> quizzes = quizDAO.findAllByParent(topicId, API.Topic.Quiz, new ParameterizedTypeReference<CollectionModel<Quiz>>() {
		});
    	
    	if (quizzes != null) {
    		theTopic.setQuizzes(quizzes);
    	}
    	
    	return theTopic;
    }
    
    @Override
    public List<Topic> findAll() {
    	return topicDAO.findAll(API.TOPIC_MODULE, new ParameterizedTypeReference<CollectionModel<Topic>>() {
		});
    }
    
    @Override
    public void addQuiz(Topic topic) {
    	topicDAO.addChildren(topic, topic.getQuizzes(), API.Topic.Quiz, API.Quiz.QUIZ);
    }
    
    
}
