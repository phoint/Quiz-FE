package com.fa.training.group01.domain_model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Question {
	private int id;
	private int title;
	private String Content;
	
	@JsonIgnore
	private List<Answer> answers;
	
}
