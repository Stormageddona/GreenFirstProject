package com.green.greenfirstproject.boardMaster.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
