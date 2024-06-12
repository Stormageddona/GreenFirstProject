package com.green.greenfirstproject.schedule.management;

import com.green.greenfirstproject.common.exception.DataNotFoundException;
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

    public List<ScheduleManagementGetDayRes> selScheduleManagementDay(ScheduleManagementGetDayReq p){
        List<ScheduleManagementGetDayRes> res = mapper.selScheduleManagementDay(p);
        int pageSize = p.getSize();
        int currentPage = p.getPage();
        int totalElements = res.size();
        int totalPages = (int) Math.ceil((double) totalElements / pageSize);

        for (ScheduleManagementGetDayRes list : res) {
            boolean hasMoreList = currentPage % 5 == 0 && currentPage < totalPages;
            list.setIsMorePaging(hasMoreList ? 1 : 0);
        }

        if (!res.isEmpty()) {
            ScheduleManagementGetDayRes lastElement = res.get(res.size() - 1);
            lastElement.setTotalPage(totalPages);
            lastElement.setTotalElement(totalElements);
        }

        return res;
    }

    public ScheduleManagementGetDayDetailRes selScheduleManagementDetail(ScheduleManagementGetDayDetailReq p){
        return mapper.selScheduleManagementDetail(p);
    }
}
