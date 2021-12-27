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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"subCategoryName", "subCategorySequence"}))
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subCategoryId;
    private int categoryId;
    private String subCategorySequence;
    private String subCategoryName;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonBackReference
    private Category category;
    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Subject> subjects;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subCategory")
    @JsonManagedReference
    private List<Course> course;

    public SubCategory(int categoryId, String subCategorySequence, String subCategoryName) {
        this.categoryId = categoryId;
        this.subCategorySequence = subCategorySequence;
        this.subCategoryName = subCategoryName;
    }
}