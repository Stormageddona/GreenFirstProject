package com.green.greenfirstproject.schedule.plant;

import com.green.greenfirstproject.common.exception.DataNotFoundException;
import com.green.greenfirstproject.schedule.management.model.ScheduleManagementGetDayRes;
import com.green.greenfirstproject.schedule.plant.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchedulePlantService {
    private final SchedulePlantMapper mapper;

    public int insSchedule(SchedulePlantPostReq p) throws Exception {
        int result = mapper.insSchedule(p);
        if(result == 0) throw new DataNotFoundException();
        return result;
    }
    public int updateSchedule(SchedulePlantPatchReq p) throws Exception{
        int result = mapper.updateSchedule(p);
        if(result == 0) throw new DataNotFoundException();
        return result;
    }
    public int deleteSchedule(SchedulePlantDeleteReq p) throws Exception{
        int result = mapper.deleteSchedule(p);
        if(result == 0) throw new DataNotFoundException();
        return result;
    }
    public List<SchedulePlantGetListRes> selSchedulePlantList(SchedulePlantGetListReq p){
        List<SchedulePlantGetListRes> list = mapper.getSchedulePlantsList(p);
        int pageSize = p.getSize();
        int currentPage = p.getPage();
        int totalElements = list.size();
        int totalPages = (int) Math.ceil((double) totalElements / pageSize);

        for (SchedulePlantGetListRes res : list) {
            boolean hasMoreList = currentPage % 5 == 0 && currentPage < totalPages;
            res.setIsMorePage(hasMoreList ? 1 : 0);
        }

        if (!list.isEmpty()) {
            SchedulePlantGetListRes lastElement = list.get(list.size() - 1);
            lastElement.setTotalPage(totalPages);
            lastElement.setTotalElement(totalElements);
        }
        return list;
    }
    public SchedulePlantGetDetailRes selSchedulePlant(SchedulePlantGetDetailReq p){
        return mapper.getSchedulePlantDetail(p);
    }
}
