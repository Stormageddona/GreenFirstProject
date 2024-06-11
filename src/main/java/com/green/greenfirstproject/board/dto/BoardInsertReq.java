package com.green.greenfirstproject.board.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardInsertReq
{
    @JsonIgnore
    private Long seq ;
    private String title ;
    private String contents ;
    @Schema(description = "유저 PK값 추가. 단 호환성을 위해 추가됨. 이 버전에서는 어떤 값을 넣든 반영되지않음.")
    private Long writer ;
}
