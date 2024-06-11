package com.green.greenfirstproject.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ResultDtoList<T> implements Result
{
    private Integer code = 1;
    private String msg = "정상 처리 되었습니다.";
    private List<T> data;
    private Integer totalElements ;
    private Integer totalPages ;

    @Builder
    public ResultDtoList(Integer code, String msg, List<T> data, Integer totalElements, Integer PAGE_SIZE)
    {
        this.code = code != null ? code : 1;
        this.msg = msg != null ? msg : "정상 처리 되었습니다.";
        this.data = data;
        this.totalElements = (data != null) ? data.size() : 0;
        this.totalPages =  (totalElements + PAGE_SIZE - 1) / PAGE_SIZE;
        ;
    }
}
