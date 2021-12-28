package com.MultipleTableFetch.Dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentReplyUpdateDto {

    public Long commentId;
    public String commentReply;
}