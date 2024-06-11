package com.green.greenfirstproject.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Paging
{
    protected int page;
    protected int size;
    protected int startIdx;

    public Paging(Integer page, int pageSize)
    {
        if (page == null || page < 1) page = 1 ;
        this.page = page;
        this.size = pageSize;
        this.startIdx = (page - 1) * pageSize;
    }
}
