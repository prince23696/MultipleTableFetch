package com.MultipleTableFetch.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CourseRatingId;
    private Long CourseId;
    private int userId;
    private int rating;
}
