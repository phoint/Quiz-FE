package com.fa.training.group01.domain_model;

<<<<<<< HEAD
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

=======
import lombok.Data;

import java.io.Serializable;

@Data
public class Topic implements Serializable {
    private Integer id;
    private String topic_name;
    private String description;
>>>>>>> 9c11c62820a734a992e402cd8d6f76a052eb4481
}
