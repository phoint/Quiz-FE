package com.fa.training.group01.domain_model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Part {
	private int id;
	private String content;
	
	@JsonIgnore
	private List<Section> sections = new ArrayList<Section>();
}
