package com.green.greenfirstproject.boardMaster.model;

import com.green.greenfirstproject.common.model.Paging;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class BoardGetReq extends Paging {
    private Integer order;

    private Integer search;

    private String keyword;




    public BoardGetReq(String keyword, Integer page, Integer size){
        super(page , size);
        this.keyword = keyword ==  null? "" : keyword;

    }
}
