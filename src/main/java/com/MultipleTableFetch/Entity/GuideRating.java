package com.MultipleTableFetch.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuideRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guideRatingId;
    private int communicationRating;
    private int explanationRating;
    private int userId;
    private int preparationRating;
    private int skillsRating;
    private int teachingMethodRating;
    private String detailedReview;
    // id:0   sessionId:0
}