package com.green.greenfirstproject.opendata.model;

import com.green.greenfirstproject.common.page.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlantDataGetReq extends Paging
{
    @Schema(example = "꽃 이름", description = "검색 키워드")
    private String keyword;
    public PlantDataGetReq(Integer page, Integer size, String keyword) {
        super(page, size);
        this.keyword = keyword;
    }
}
