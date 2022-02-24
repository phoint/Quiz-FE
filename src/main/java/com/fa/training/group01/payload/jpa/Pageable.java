package com.fa.training.group01.payload.jpa;

import lombok.Data;

@Data
public class Pageable {
	private Sort sort;
	private int offset;
	private int pageSize;
	private int pageNumber;
	private boolean unpaged;
	private boolean paged;
}
