package com.green.greenfirstproject.schedule.management.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Date;

@Data
public class ScheduleManagementGetDayDetailReq {
    private String plantManagementSeq;
    @Schema(description = "해당 년월일")
    private String managementDate;
}
