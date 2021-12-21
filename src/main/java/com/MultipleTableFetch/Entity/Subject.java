package com.MultipleTableFetch.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subjectId;
    private int subCategoryId;
    private String subjectSequence;
    private String subjectName;
    @ManyToOne
    @JsonBackReference
    private SubCategory subCategory;

    public Subject(int subCategoryId, String subjectSequence, String subjectName) {
        this.subCategoryId = subCategoryId;
        this.subjectSequence = subjectSequence;
        this.subjectName = subjectName;
    }
}