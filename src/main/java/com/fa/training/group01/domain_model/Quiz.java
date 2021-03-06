package com.fa.training.group01.domain_model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Quiz extends GenericModel {
	private String title;
	private String content;
	
	private Topic topic;
	
	@JsonIgnore
	private List<Part> parts = new ArrayList<Part>();
	
	@JsonIgnore
	private List<Question> questions = new ArrayList<Question>();
	
	@JsonIgnore
	public Topic getTopic() {
		return this.topic;
	}
	@JsonProperty
	public void setTopic(Topic topic) {
		this.topic = topic;
	}

}
