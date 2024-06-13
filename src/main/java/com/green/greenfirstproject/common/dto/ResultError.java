package com.green.greenfirstproject.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultError implements Result {
    @Builder.Default
    private Integer code = -1;
    @Builder.Default
    private String msg = "에러가 발생했습니다.";

}
