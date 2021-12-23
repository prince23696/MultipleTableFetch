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
public class FAQQuestionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int faqQuestionId;
    private int faqTopicId;
    private String faqQuestion;
    private String faqAnswer;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonBackReference
    private FAQTopic faqTopic;

    public FAQQuestionAnswer(int faqTopicId, String faqQuestion, String faqAnswer) {
        this.faqTopicId = faqTopicId;
        this.faqQuestion = faqQuestion;
        this.faqAnswer = faqAnswer;
    }
}