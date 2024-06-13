package com.green.greenfirstproject.boardComment.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardCommentPostReq {
    @JsonIgnore
    private long cmtSeq;

    private long boardSeq;
    private long writer;
    private String content;
}


