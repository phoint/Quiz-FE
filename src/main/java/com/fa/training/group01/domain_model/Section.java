package com.fa.training.group01.domain_model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Section {
	private int id;
	private String content;
	
	@JsonIgnore
	private List<Question> questions;
}
