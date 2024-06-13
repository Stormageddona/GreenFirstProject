package com.green.greenfirstproject.boardcomment.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BoardCommentPatchReq {
    private long commentSeq;
    private long writer;
    private String content;
}
