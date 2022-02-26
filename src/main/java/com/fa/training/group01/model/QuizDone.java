package com.fa.training.group01.model;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class QuizDone {
	private Counter counter = new Counter(-1); 
	private int quizId;
	private List<UserChoice> choices;
	
	
	
	public void createHolder(int size) {
		this.choices = Arrays.asList(new UserChoice[size]);
	}
}
