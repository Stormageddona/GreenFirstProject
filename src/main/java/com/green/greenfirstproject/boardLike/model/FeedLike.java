package com.green.greenfirstproject.boardLike.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedLike {

    public FeedLike(long boardSeq, long writer ) {
        this.boardSeq = boardSeq;
        this.writer  = writer;
    }

    private long boardSeq;

    private long writer ;
}
