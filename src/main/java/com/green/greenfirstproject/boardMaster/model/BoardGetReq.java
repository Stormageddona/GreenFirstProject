package com.green.greenfirstproject.boardMaster.model;

import com.green.greenfirstproject.common.GlobalConst;
import com.green.greenfirstproject.common.model.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
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
