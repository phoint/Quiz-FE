package com.fa.training.group01.domain_model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
public class Question extends GenericModel {
	private String title;
	private String content;
	private int answerIndex;

	@JsonIgnore
	private int sectionId;

	@JsonIgnore
	private List<Answer> answers = new ArrayList<Answer>();

}
