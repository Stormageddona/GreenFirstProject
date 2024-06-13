package com.green.greenfirstproject.boardcomment.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter


public class BoardCommentGetRes {
    private long cmtSeq;
    private long cmtBoardSeq;
    private long cmtUserSeq;
    private String cmtText;
    private String inputDt;
    private String updateDt;
    private String content;
    private long writerSeq;
    private String writerName;
}
