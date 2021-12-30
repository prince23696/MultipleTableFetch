package com.MultipleTableFetch.Dto;

import com.MultipleTableFetch.Entity.GuideRating;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GuideRatingResponseDto {

    private int recordCount;
    private List<GuideRating> guideRatingsList;
    private GuideRating guideRatingDto;
}