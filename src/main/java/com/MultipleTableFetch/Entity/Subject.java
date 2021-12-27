package com.MultipleTableFetch.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
//@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"subjectName", "subjectSequence"}))
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subjectId;
    private int subCategoryId;
    private String subjectSequence;
    private String subjectName;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonBackReference
    private SubCategory subCategory;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
    @JsonManagedReference
    private List<Course> course;

    public Subject(int subCategoryId, String subjectSequence, String subjectName) {
        this.subCategoryId = subCategoryId;
        this.subjectSequence = subjectSequence;
        this.subjectName = subjectName;
    }
}