package com.green.greenfirstproject.boardComment.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class BoardCommentGetRes {
    private long cmtSeq;
    private long cmtBoardSeq;
    private String cmtText;
    private String inputDt;
    private long writerSeq;
    private String writerName;

    public void setInputDt(LocalDateTime inputDt)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.inputDt = inputDt.format(formatter);

    }
}
