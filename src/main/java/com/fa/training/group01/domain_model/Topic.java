package com.fa.training.group01.domain_model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Topic implements Serializable {
    private Integer id;
    private String topic_name;
    private String description;
}
