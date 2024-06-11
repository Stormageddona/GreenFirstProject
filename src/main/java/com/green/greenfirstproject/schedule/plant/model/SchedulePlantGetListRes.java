package com.green.greenfirstproject.schedule.plant.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchedulePlantGetListRes {
    @Schema(name = "애칭")
    private String plantNickName;
    private String plantPic;
    private String plantName;

    @Schema(description = "총 페이지 수")
    private int totalPage;
    @Schema(description = "총 등록된 식물 수")
    private int totalElement;
}
