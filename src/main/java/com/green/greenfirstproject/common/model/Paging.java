package com.green.greenfirstproject.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;

import static com.green.greenfirstproject.common.GlobalConst.PAGING_LIST;
import static com.green.greenfirstproject.common.GlobalConst.PAGING_SIZE;

@Getter
@Setter
public class Paging {
    @JsonIgnore
    private Integer size;
    private Integer page;
    @JsonIgnore
    private Integer startIdx;

    @ConstructorProperties({"page", "size"})
    public Paging(Integer page,Integer size ){
        this.page = page == null ||  page < 1 ? PAGING_LIST  : page -1;
        this.size = size == null ||  size < 1 ? PAGING_SIZE : size;
        this.startIdx =this.page * this.size;
    }
}
