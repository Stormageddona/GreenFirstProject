package com.green.greenfirstproject.boardcomment.common;

import com.green.greenfirstproject.common.Paging;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BoardCommentGetReq {
    private long boardSeq;
    private long page;
    }

