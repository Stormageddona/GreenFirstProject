package com.green.greenfirstproject.schedule.management;

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

    public int insScheduleManagement(ScheduleManagementPostReq p){
        return mapper.insScheduleManagement(p);
    }
    public int delScheduleManagement(ScheduleManagementDeleteReq p){
        return mapper.delScheduleManagement(p);
    }
    public int updateScheduleManagement(ScheduleManagementPatchReq p){
        return mapper.updateScheduleManagement(p);
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
            list.setIsMoreComment(hasMoreList ? 1 : 0);
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
