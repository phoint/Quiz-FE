package com.fa.training.group01.domain_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Excel implements Serializable {
    private String qTitle;
    private String qContent;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;
    private String correct_ans;
    private String linkImg;
    private String time;
}
