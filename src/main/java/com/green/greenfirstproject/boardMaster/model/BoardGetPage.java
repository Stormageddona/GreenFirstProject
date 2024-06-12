package com.green.greenfirstproject.boardMaster.model;

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardGetPage {
   private List<BoardGetRes> list;

   private Integer totalPage;
   private Integer totalElements;


}
