package com.green.greenfirstproject.schedule.management.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ScheduleManagementGetMonthReq {
    private long plantManagementSeq;
    @Schema(description = "해당 월")
    private String managementDate;
}
