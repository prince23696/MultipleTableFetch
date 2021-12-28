package com.MultipleTableFetch.Dto;

import com.MultipleTableFetch.Entity.CommentReply;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentReplyResponseDtoQuery {

    private int recordCount;
    private List<CommentReply> commentReplyList;

}
