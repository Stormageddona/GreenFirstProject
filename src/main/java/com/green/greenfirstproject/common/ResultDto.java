package com.green.greenfirstproject.common;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ResultDto<T> {
    private Integer Code;
    private String resultMsg;
    private T resultData;




    public static <T> ResultDto<T> resultDto(Integer code, String msg, T data) {
        return ResultDto.<T>builder()
                .Code(code)
                .resultMsg(msg)
                .resultData(data)
                .build();
    }
    public static <T> ResultDto<T> resultDto1(Integer code, String msg) {
        return ResultDto.<T>builder()
                .Code(code)
                .resultMsg(msg)
                .build();
    }
}



