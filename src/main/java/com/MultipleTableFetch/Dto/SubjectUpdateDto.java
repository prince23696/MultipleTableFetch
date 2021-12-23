package com.MultipleTableFetch.Dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubjectUpdateDto {

    private int subCategoryId;
    private String subjectSequence;
    private String subjectName;
}
