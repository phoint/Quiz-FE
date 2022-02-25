package com.fa.training.group01.domain_model.Ex;

import com.fa.training.group01.domain_model.GenericModel;
import com.fa.training.group01.domain_model.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SectionEx extends GenericModel {
    private String content;
    private List<QuestionEx> questions = new ArrayList<QuestionEx>();

    public SectionEx(String content) {
        this.content = content;
    }
}
