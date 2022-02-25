package com.fa.training.group01.domain_model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Topic extends GenericModel {
	private String name;
	private String description;

	@JsonIgnore
	private List<Quiz> quizzes;

}
