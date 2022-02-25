package com.fa.training.group01.domain_model.Ex;

import com.fa.training.group01.domain_model.Answer;
import com.fa.training.group01.domain_model.GenericModel;
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
public class QuestionEx extends GenericModel {
    private String title;
    private String content;
    private int answerIndex;

    @JsonIgnore
    private int sectionId;

    private List<AnswerEx> answers = new ArrayList<AnswerEx>();

    public QuestionEx(String title, String content, int answerIndex, List<AnswerEx> answers) {
        this.title = title;
        this.content = content;
        this.answerIndex = answerIndex;
        this.answers = answers;
    }
}
