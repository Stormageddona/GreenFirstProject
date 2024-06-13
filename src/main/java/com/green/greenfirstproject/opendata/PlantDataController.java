package com.green.greenfirstproject.opendata;

import com.green.greenfirstproject.common.ResultDto;
import com.green.greenfirstproject.opendata.model.PlantDataGetPage;
import com.green.greenfirstproject.opendata.model.PlantDataGetReq;
import com.green.greenfirstproject.opendata.model.PlantDataGetRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import static com.green.greenfirstproject.common.GlobalConst.SUCCESS_CODE;

@Slf4j
@RestController
@RequestMapping("api/opendata/")
@RequiredArgsConstructor
@Tag(name = "식물 검색", description = "식물 공공데이터 Read")
public class PlantDataController {
    private final OpenDataWebClientService webClientService;
    private final PlantDataService service;

    @GetMapping
    @Operation(summary = "식물 검색")
    public ResultDto<PlantDataGetPage> getPlantData(@ParameterObject @ModelAttribute PlantDataGetReq p) {
        PlantDataGetPage res = service.getPlantData(p);
        return ResultDto.resultDto(SUCCESS_CODE, "검색 완료", res);
    }

    @GetMapping("manual")
    @Operation(summary = "공공데이터 수동 업데이트(테스트용)")
    public Boolean manualPlantData()
    {
        try {
            webClientService.deleteAllPlantData();
            webClientService.getOpenData();

        } catch (Exception e) {
            log.error("An error occurred: ", e);
            return false;
        }
        return true ;
    }
}