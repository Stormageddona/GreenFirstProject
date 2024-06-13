package com.green.greenfirstproject.boardLike.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class FeedFavoriteEntity {
    private long boardSeq;
    private long writer;
    private String inputDt;

    public FeedFavoriteEntity(long boardSeq, long writer) {
        this.boardSeq = boardSeq;
        this.writer = writer;
    }
}
