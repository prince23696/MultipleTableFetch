package com.MultipleTableFetch.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private int userId;
    private String courseName;
    private String courseSubName;
    private String endDate;
    private String startDate;
    private int categoryId;
    private int subCategoryId;
    private int subjectId;
    private String description;
    private String currencyType;
    private Long price;
    private String status;
}