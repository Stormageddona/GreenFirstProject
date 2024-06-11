package com.green.greenfirstproject.boardMaster.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardGetPage {
   private List<BoardGetRes> boardGetRes;

   private int totalPage;
   private int totalElements;


}
