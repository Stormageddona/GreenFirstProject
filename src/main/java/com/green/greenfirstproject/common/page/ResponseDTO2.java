package com.green.greenfirstproject.common.page;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDTO2<T> {
    private List<T> list;
    private Integer totalPage;
    private Integer totalElement;

    public ResponseDTO2(List<T> list, Integer size, Integer totalElement) {
        this.list = list;
        this.totalElement = totalElement;
        this.totalPage = ( totalElement + size - 1 ) / size ;
    }
}
