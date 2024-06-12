package com.green.greenfirstproject.opendata;

import com.green.greenfirstproject.common.ResultDto;
import com.green.greenfirstproject.opendata.model.PlantData;
import com.green.greenfirstproject.opendata.model.PlantDataGetReq;
import com.green.greenfirstproject.opendata.model.PlantDataGetRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.green.greenfirstproject.common.GlobalConst.SUCCESS_CODE;

@Slf4j
@RestController
@RequestMapping("api/opendata/")
@RequiredArgsConstructor
@Tag(name = "식물 검색", description = "식물 공공데이터 Read")
public class PlantDataController {
    private final PlantDataService service;

    @GetMapping
    @Operation(summary = "식물 검색")
    public ResultDto<List<PlantDataGetRes>> getPlantData(@ParameterObject @ModelAttribute PlantDataGetReq p) {
        List<PlantDataGetRes> res = service.getPlantData(p);
        return ResultDto.resultDto(SUCCESS_CODE, "검색 완료", res);
    }
}
