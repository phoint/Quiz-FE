package com.fa.training.group01.payload.jpa;

import java.util.List;

import com.fa.training.group01.domain_model.Model;

import lombok.Data;

@Data
public class EntityPageResponse<T extends Model> {
	private List<T> content;
	private Pageable pageable;
	private boolean last;
	private int totalPages;
	private int totalElements;
	private int size;
	private int number;
	private Sort sort;
	private boolean first;
	private int numberOfElements;
	private boolean empty;

}
