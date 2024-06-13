package com.green.greenfirstproject.boardComment.common;

import com.green.greenfirstproject.common.model.Paging;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BoardCommentGetReq extends Paging
{
    private long boardSeq;

    public BoardCommentGetReq(long boardSeq, Integer page, Integer size)
    {
        super(page,size);
        this.boardSeq = boardSeq;

    }
}

