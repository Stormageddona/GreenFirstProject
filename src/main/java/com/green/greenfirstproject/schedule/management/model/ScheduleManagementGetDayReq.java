package com.green.greenfirstproject.schedule.management.model;

import com.green.greenfirstproject.common.page.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class ScheduleManagementGetDayReq extends Paging {
    private long userSeq;
    @Schema(description = "해당 년월일")
    private String managementDate ;

    public ScheduleManagementGetDayReq(int page, int size) {
        super(page, size);
    }
}
