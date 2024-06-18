package com.green.greenfirstproject.schedule.management.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
public class ScheduleManagementGetMonthReq {
    private long userSeq;
    @Schema(description = "해당 년월일")
    private String managementDate;
}
