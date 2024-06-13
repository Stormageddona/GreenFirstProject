package com.green.greenfirstproject.boardMaster.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BoardReq {
    @JsonIgnore
    private  long boardSeq;

    private String title;

    private String content;

    private long writerSeq;

}
