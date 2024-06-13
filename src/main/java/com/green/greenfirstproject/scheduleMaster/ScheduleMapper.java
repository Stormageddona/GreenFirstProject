package com.green.greenfirstproject.scheduleMaster;

import com.green.greenfirstproject.scheduleMaster.model.postSchedule;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface ScheduleMapper {
    int postSchedule(postSchedule p);

}
