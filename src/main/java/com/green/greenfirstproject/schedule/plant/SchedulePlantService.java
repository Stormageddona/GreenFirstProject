package com.green.greenfirstproject.schedule.plant;

import com.green.greenfirstproject.common.GlobalConst;
import com.green.greenfirstproject.common.exception.DataNotFoundException;
import com.green.greenfirstproject.common.page.ResponseDTO;
import com.green.greenfirstproject.common.page.ResponseDTO2;
import com.green.greenfirstproject.schedule.management.model.ScheduleManagementGetDayRes;
import com.green.greenfirstproject.schedule.plant.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
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
    public ResponseDTO2 selSchedulePlantList(SchedulePlantGetListReq p){
        List<SchedulePlantGetListRes> list = mapper.getSchedulePlantsList(p);
        int total = mapper.getTotal(p);
        ResponseDTO2 dto = new ResponseDTO2(list, p.getSize(), mapper.getTotal(p));
        boolean hasNextPage = ( p.getPage() * GlobalConst.SIZE_NUM < total);
        for(SchedulePlantGetListRes res : list){
            boolean inMorePage = (p.getPage() % 5 == 0) && hasNextPage ;
            res.setIsMorePage( inMorePage ? 1 : 0 );
        }

        return dto;
    }
    public SchedulePlantGetDetailRes selSchedulePlant(SchedulePlantGetDetailReq p){
        return mapper.getSchedulePlantDetail(p);
    }
}
