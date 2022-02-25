package com.fa.training.group01.domain_model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TopicPage implements Serializable {
    private Object[] content;
    private Long TotalElements;
    private int TotalPage;
}
