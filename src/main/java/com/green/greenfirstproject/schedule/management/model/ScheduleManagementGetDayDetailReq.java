package com.green.greenfirstproject.schedule.management.model;

import com.green.greenfirstproject.common.page.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleManagementGetDayDetailReq extends Paging {
    private String plantManagementSeq;
    @Schema(description = "해당 년월일")
    private String managementDate;

    public ScheduleManagementGetDayDetailReq(Integer page, Integer size) {
        super(page, size);
    }
}
