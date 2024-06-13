package com.green.greenfirstproject.boardComment.common;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;

@Getter
@Setter

public class BoardCommentDeleteReq {
    private long commentSeq;
    private long writer;


}
/*
@ConstructorProperties({"cmt_seq", "cmt_user_id"})
    BoardCommentDeleteReq(long cmtSeq, long cmtUserSeq) {
        this.commentSeq = cmtSeq;
        this.writer = cmtUserSeq;
    }
 */
