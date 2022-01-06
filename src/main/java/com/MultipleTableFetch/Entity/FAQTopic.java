package com.MultipleTableFetch.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FAQTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int faqTopicId;
    private int faqCategoryId;
    private String faqTopicName;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonBackReference
    private FAQCategory faqCategory;
    @OneToMany(mappedBy = "faqTopic", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<FAQQuestionAnswer> faqQuestionAnswerList;

    public FAQTopic(int faqCategoryId, String faqTopicName) {
        this.faqCategoryId = faqCategoryId;
        this.faqTopicName = faqTopicName;
    }
}