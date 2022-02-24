package com.fa.training.group01.domain_model;

import java.util.ArrayList;
import java.util.List;

import com.fa.training.group01.model.Counter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QuizTaken extends GenericModel {
	private int scored;
	
	@JsonIgnore
	private Counter counter = new Counter(-1);
	
	@JsonIgnore
	private int maxScored;
	
	@JsonIgnore
	private List<AnswerTaken> answerTaken = new ArrayList<AnswerTaken>();
}
