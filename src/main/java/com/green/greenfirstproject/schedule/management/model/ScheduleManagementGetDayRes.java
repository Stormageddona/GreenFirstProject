package com.green.greenfirstproject.schedule.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class ScheduleManagementGetDayRes {
    private long gardenSeq;
    private int gardening;
    private String plantPic;
    private String plantName;
    @Schema(description = "특정 전체 DATE")
    private String managementDate;
    private int isMorePaging;
}
