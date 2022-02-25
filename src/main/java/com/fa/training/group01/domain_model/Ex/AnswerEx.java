package com.fa.training.group01.domain_model.Ex;

import com.fa.training.group01.domain_model.GenericModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AnswerEx extends GenericModel {
    private String content;
    private boolean correct;
}
