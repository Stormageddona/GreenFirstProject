package com.green.greenfirstproject.boardMaster.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@Builder
public class BoardGetResList {
    private  int boardSeq;

    private String title;


    private  LocalDate inputDt;

    private  String writerName;

    private  int fav;

    private int hit;


}
