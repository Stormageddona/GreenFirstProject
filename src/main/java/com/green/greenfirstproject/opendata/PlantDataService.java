package com.green.greenfirstproject.opendata;

import com.green.greenfirstproject.opendata.model.PlantData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlantDataService {
    private final PlantDataMapper mapper;

    public List<PlantData> getPlantData(String plantGnrlNm) {
        List<PlantData> res = mapper.getPlantData(plantGnrlNm);
        return res;
    }
}
