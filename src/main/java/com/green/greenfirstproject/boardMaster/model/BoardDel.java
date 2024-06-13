package com.green.greenfirstproject.boardMaster.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BoardDel {

    private  long boardSeq;

    private long writerSeq;
}
