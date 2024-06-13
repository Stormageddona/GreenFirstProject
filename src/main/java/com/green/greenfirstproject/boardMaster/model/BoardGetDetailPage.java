package com.green.greenfirstproject.boardMaster.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class BoardGetDetailPage {
    private  int boardSeq;

    private String title;

    private String contents;

    private LocalDate inputDt;

    private  String writerName;

    private  int fav;

    private int hit;
}
