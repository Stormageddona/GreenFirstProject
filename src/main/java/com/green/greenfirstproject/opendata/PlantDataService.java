package com.green.greenfirstproject.opendata;

import com.green.greenfirstproject.opendata.model.PlantData;
import com.green.greenfirstproject.opendata.model.PlantDataGetReq;
import com.green.greenfirstproject.opendata.model.PlantDataGetRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlantDataService {
    private final PlantDataMapper mapper;

    public List<PlantDataGetRes> getPlantData(PlantDataGetReq p) {
        List<PlantDataGetRes> res = mapper.getPlantData(p);
        return res;
    }
}
