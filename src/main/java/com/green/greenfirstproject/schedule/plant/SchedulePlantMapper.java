package com.green.greenfirstproject.schedule.plant;

import com.green.greenfirstproject.schedule.plant.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchedulePlantMapper {
    int insSchedule(SchedulePlantPostReq p);
    int updateSchedule(SchedulePlantPatchReq p);
    int deleteSchedule(SchedulePlantDeleteReq p);
    List<SchedulePlantGetListRes> getSchedulePlantsList(SchedulePlantGetListReq p);
    SchedulePlantGetDetailRes getSchedulePlantDetail(SchedulePlantGetDetailReq p);

    Integer findPageInfo(SchedulePlantGetListReq p);
    int getTotal(SchedulePlantGetListReq p);
}
