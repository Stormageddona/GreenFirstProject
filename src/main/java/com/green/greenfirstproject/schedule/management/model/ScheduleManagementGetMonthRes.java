package com.green.greenfirstproject.schedule.management.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class ScheduleManagementGetMonthRes {
    private int gardening;
    @Schema(description = "해당 월")
    private String managementDate;
}
