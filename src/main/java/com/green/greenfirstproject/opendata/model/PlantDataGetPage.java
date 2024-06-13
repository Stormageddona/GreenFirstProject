package com.green.greenfirstproject.opendata.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlantDataGetPage {
    private List<PlantDataGetRes> list;
    private long totalPage; // 총 페이지 갯수
    private long totalElements; // 총 개체의 갯수 (총갯수 + 사이즈 - 1)/사이즈

    public PlantDataGetPage(long totalElements, Integer size, List<PlantDataGetRes> list) {
        this.totalElements = totalElements;
        this.totalPage = (this.totalElements + size - 1) / size;
        this.list = list;
    }
}
