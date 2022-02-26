package com.fa.training.group01.domain_model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AnswerTaken extends GenericModel {
	@JsonIgnore
	private Question question;
	@JsonIgnore
	private Answer answer;
}
