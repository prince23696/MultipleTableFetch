package com.MultipleTableFetch.Dto;

import com.MultipleTableFetch.Entity.Course;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDtoQuery {

    private int recordCount;
    private Page<Course> courseList;
    private List<Course> courseStatusDtoList;

}
