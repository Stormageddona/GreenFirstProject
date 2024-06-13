package com.green.greenfirstproject.boardComment.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BoardCommentPatchReq {
    private long commentSeq;
    private long writer;
    private String content;
}
