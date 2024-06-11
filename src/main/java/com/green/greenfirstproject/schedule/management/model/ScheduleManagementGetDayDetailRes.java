package com.green.greenfirstproject.schedule.management.model;

import lombok.Data;

@Data
public class ScheduleManagementGetDayDetailRes {
    private String managementDate;
    private int gardening;
    private String plantPic;
    private String plantName;
    private String content;
}
