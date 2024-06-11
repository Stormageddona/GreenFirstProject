package com.green.greenfirstproject.board;

import com.green.greenfirstproject.board.dto.BoardGetDto;
import com.green.greenfirstproject.board.dto.BoardInsertReq;
import com.green.greenfirstproject.board.model.BoardViewList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper
{
    void insertBoardData(BoardInsertReq data) ;
    int deleteBoardData() ;
    void updateBoardData() ;
    void addBoardHits() ;
    List<BoardViewList> selectBoardListByTitle(BoardGetDto data) ;
    List<BoardViewList> selectBoardListByContent(BoardGetDto data) ;
    int countByTotalElements(String keyword, Integer type) ;
    void selectBoardDetail() ;
}
