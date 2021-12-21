package com.MultipleTableFetch.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subCategoryId;
    private int categoryId;
    private String subCategorySequence;
    private String subCategoryName;
    @ManyToOne
    @JsonBackReference
    private Category category;
    @OneToMany
    @JsonManagedReference
    private List<Subject> subjects;

    public SubCategory(int categoryId, String subCategorySequence, String subCategoryName) {
        this.categoryId = categoryId;
        this.subCategorySequence = subCategorySequence;
        this.subCategoryName = subCategoryName;
    }
}