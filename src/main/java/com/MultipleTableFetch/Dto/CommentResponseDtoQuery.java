package com.MultipleTableFetch.Dto;

import com.MultipleTableFetch.Entity.Comment;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDtoQuery {

    private int recordCount;
    private List<Comment> commentList;

}
