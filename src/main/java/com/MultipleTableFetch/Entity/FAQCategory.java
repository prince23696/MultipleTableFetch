package com.MultipleTableFetch.Entity;

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
public class FAQCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int faqCategoryId;
    private String categoryName;
    @OneToMany(mappedBy = "faqCategory", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<FAQTopic> faqTopicList;

    public FAQCategory(String categoryName) {
        this.categoryName = categoryName;
    }
}