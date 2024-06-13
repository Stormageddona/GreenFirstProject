package com.green.greenfirstproject.boardcomment.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class BoardCommentGetPage {
    private List<BoardCommentGetRes> list;
    private long totalPage;
    private long totalElements;

    public BoardCommentGetPage(List<BoardCommentGetRes> list, Integer size, long totalElements) {
        this.list = list;
        this.totalElements = totalElements;
        this.totalPage = (this.totalElements + size - 1) / size;
    }
}
