package com.green.greenfirstproject.boardMaster.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@Builder
public class BoardGetRes {
    private  int boardSeq;
    private String title;
    private  String writerName;
    private  LocalDate inputDt;
    private  int fav;
    private int hit;

}
