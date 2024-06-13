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
    private Integer size;
    private Integer page;
    @JsonIgnore
    private Integer startIdx;

    @ConstructorProperties({"page", "size"})
    public Paging(Integer page,Integer size ){
        System.out.println(size);
        this.page = page == null ||  page < 1 ? PAGING_LIST : page;
        this.size = size == null ||  size < 1 ? PAGING_SIZE : size;
        this.startIdx =this.page;
    }
}
