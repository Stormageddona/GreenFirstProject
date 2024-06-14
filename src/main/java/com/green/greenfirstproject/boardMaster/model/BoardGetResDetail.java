package com.green.greenfirstproject.boardMaster.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class BoardGetResDetail {
    @JsonIgnore
    private  int boardSeq;

    private List<BoardGetDetailPage> list;

    private Integer totalPage;
    private Integer totalElements;
}
