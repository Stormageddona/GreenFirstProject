package com.green.greenfirstproject.schedule.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.greenfirstproject.common.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleManagementGetDayDetailRes extends Paging {
    private String managementDate;
    private int gardening;
    private String plantPic;
    private String plantName;
    private String content;

    public ScheduleManagementGetDayDetailRes(Integer page, Integer size, int gardening, String plantPic, String plantName) {
        super(page, size);
        this.gardening = gardening;
        this.plantPic = plantPic;
        this.plantName = plantName;
        this.startIdx = page - 1 < 0 ? 0 : ( page - 1 ) * size;
    }
    @JsonIgnore
    private Integer startIdx;
    private int isMorePage;
    @Schema(description = "총 페이지 수")
    private int totalPage;
    @Schema(description = "총 등록된 일정 수")
    private int totalElement;
}
