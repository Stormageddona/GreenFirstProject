package com.green.greenfirstproject.schedule.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.greenfirstproject.common.page.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


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
