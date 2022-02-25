package com.fa.training.group01.domain_model.Ex;

import com.fa.training.group01.domain_model.GenericModel;
import com.fa.training.group01.domain_model.Part;
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
public class QuizEx extends GenericModel {
    private String title;
    private String content;
    private List<PartEx> parts = new ArrayList<PartEx>();

    public QuizEx(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
