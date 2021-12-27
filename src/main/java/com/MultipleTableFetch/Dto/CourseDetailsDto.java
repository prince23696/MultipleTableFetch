package com.MultipleTableFetch.Dto;

public interface CourseDetailsDto {

    int getCourseId();

    int getUserId();

    String getCourseName();

    String getCourseSubName();

    String getEndDate();

    String getStartDate();

    int getCategoryId();

    int getSubCategoryId();

    int getSubjectId();

    String getDescription();

    String getCurrencyType();

    Long getPrice();

    String getStatus();
}