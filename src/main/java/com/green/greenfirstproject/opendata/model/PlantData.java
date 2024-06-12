package com.green.greenfirstproject.opendata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.green.greenfirstproject.common.Paging;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PlantData
{
    private Long plantPilbkNo;
    private String imgUrl;
    private String plantGnrlNm;

    public void setImgUrl(String imgUrl)
    {
        if (imgUrl.equals("NONE")) imgUrl = null;
        this.imgUrl = imgUrl;
    }
}
