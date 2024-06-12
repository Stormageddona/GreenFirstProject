package com.green.greenfirstproject.schedule.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.greenfirstproject.common.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class ScheduleManagementGetDayRes extends Paging {
    private int gardening;
    private String plantPic;
    private String plantName;
    @Schema(description = "특정 전체 DATE")
    private String managementDate;

    public ScheduleManagementGetDayRes(Integer page, Integer size, int gardening, String plantPic, String plantName) {
        super(page, size);
        this.gardening = gardening;
        this.plantPic = plantPic;
        this.plantName = plantName;
        this.startIdx = page - 1 < 0 ? 0 : ( page - 1 ) * size;
    }
    @JsonIgnore
    private Integer startIdx;
    private int isMorePaging;
    @Schema(description = "총 페이지 수")
    private int totalPage;
    @Schema(description = "총 등록된 일정 수")
    private int totalElement;
}
