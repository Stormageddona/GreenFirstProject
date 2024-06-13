package com.green.greenfirstproject.schedule.management;

import com.green.greenfirstproject.common.exception.DataNotFoundException;
import com.green.greenfirstproject.common.page.ResponseDTO2;
import com.green.greenfirstproject.schedule.management.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleManagementService {
    private final ScheduleManagementMapper mapper;

    public int insScheduleManagement(ScheduleManagementPostReq p) throws Exception {
        int result = mapper.insScheduleManagement(p);
        if(result == 0) throw new DataNotFoundException();
        return result;
    }

    public int delScheduleManagement(ScheduleManagementDeleteReq p) throws Exception {
        int deleteResult = mapper.delScheduleManagement(p);
        if(deleteResult == 0) throw new DataNotFoundException();
        return deleteResult;
    }

    public int updateScheduleManagement(ScheduleManagementPatchReq p) throws Exception {
        int result = mapper.updateScheduleManagement(p);
        if(result == 0) throw new DataNotFoundException();
        return result;
    }

    public List<ScheduleManagementGetMonthRes> getScheduleManagementMonth(ScheduleManagementGetMonthReq p){
        return mapper.selScheduleManagementMonth(p);
    }

    public ResponseDTO2 selScheduleManagementDay(ScheduleManagementGetDayReq p){
        List<ScheduleManagementGetDayRes> list = mapper.selScheduleManagementDay(p);
        ResponseDTO2 dto = new ResponseDTO2(list, p.getSize(), mapper.getTotalElement(p));

        return dto;
    }

    public ScheduleManagementGetDayDetailRes selScheduleManagementDetail(ScheduleManagementGetDayDetailReq p){
        return mapper.selScheduleManagementDetail(p);
    }
}
