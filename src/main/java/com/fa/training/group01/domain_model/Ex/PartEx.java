package com.fa.training.group01.domain_model.Ex;

import com.fa.training.group01.domain_model.GenericModel;
import com.fa.training.group01.domain_model.Section;
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
public class PartEx extends GenericModel {
    private String content;

    private List<SectionEx> sections = new ArrayList<SectionEx>();

    public PartEx(String content) {
        this.content = content;
    }
}
