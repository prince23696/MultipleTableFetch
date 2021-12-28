package com.MultipleTableFetch.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private int userId;
    private Long courseId;
    private String comment;
}