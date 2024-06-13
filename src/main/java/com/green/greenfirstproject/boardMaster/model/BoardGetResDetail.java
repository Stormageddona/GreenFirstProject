package com.green.greenfirstproject.boardMaster.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class BoardGetResDetail {


    private  int boardSeq;

    private String title;

    private String contents;

    private LocalDate inputDt;

    private  String writerName;

    private  int fav;

    private int hit;

}
