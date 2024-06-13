package com.green.greenfirstproject.common.page;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDTO<T> {
    private List<T> list;
    private Integer totalPage;
    private Integer totalElement;
}
