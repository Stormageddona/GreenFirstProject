package com.green.greenfirstproject.board.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardViewList
{
    private Long boardSeq ;
    private String title ;
    private Long hit ;
//    private Integer commentCount ;
    private String writerName ;
    private String inputDt ;
    private Integer fav ;

}
