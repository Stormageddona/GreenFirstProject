package com.green.greenfirstproject.boardMaster.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardGetPage {

   private  int boardSeq;

   private List<BoardGetResList> list;


   private Integer totalPage;

   private Integer totalElements;


}
