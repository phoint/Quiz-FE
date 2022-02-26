package com.fa.training.group01.domain_model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QuizCreator extends GenericModel {

	private String email;
	private String name;
	@JsonIgnore
	private List<Quiz> quizzes;
}
