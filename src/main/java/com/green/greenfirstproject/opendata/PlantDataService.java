package com.green.greenfirstproject.opendata;

import com.green.greenfirstproject.opendata.model.PlantDataGetPage;
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

    public PlantDataGetPage getPlantData(PlantDataGetReq p) {
        List<PlantDataGetRes> res = mapper.getPlantData(p);
        PlantDataGetPage page = new PlantDataGetPage(
                mapper.getTotalElements(p.getKeyword())
                , p.getSize()
                , res);
        return page;
    }
}
