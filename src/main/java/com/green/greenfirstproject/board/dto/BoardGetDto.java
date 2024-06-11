package com.green.greenfirstproject.board.dto;

import com.green.greenfirstproject.common.dto.Paging;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardGetDto extends Paging
{
    private String keyword;

    public BoardGetDto(String keyword , Integer page, Integer size)
    {
        super(page, size) ;
        this.keyword = keyword == null ? "" : keyword;
    }
}
