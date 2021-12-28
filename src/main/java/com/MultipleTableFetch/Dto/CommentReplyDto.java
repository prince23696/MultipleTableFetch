package com.MultipleTableFetch.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentReplyDto {
    private Long commentId;
    private int userId;
    private String commentReply;
}