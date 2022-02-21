package com.fa.training.group01.domain_model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Part extends GenericModel {
	private String content;

	@JsonIgnore
	private List<Section> sections = new ArrayList<Section>();
}
