package com.green.greenfirstproject.opendata;

import com.green.greenfirstproject.opendata.model.PlantData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlantDataMapper
{
    void insertPlantData(List<PlantData> data) ;
    void deleteAllPlantData() ;
}
