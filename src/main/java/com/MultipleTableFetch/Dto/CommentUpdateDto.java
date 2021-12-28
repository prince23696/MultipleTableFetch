package com.MultipleTableFetch.Dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentUpdateDto {

    public Long courseId;
    public int userId;
    public String comment;
}