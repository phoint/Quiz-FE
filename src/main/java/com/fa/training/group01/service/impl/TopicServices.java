package com.fa.training.group01.service.impl;

import com.fa.training.group01.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TopicServices<K,T> {
    @Autowired
    AppConfig appConfig;

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
}
