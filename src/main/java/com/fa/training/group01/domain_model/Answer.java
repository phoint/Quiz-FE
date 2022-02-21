package com.fa.training.group01.domain_model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Answer extends GenericModel{
	private String content;
	private boolean correct;
}
