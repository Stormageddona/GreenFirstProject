package com.green.greenfirstproject.board;

import com.green.greenfirstproject.board.dto.BoardGetDto;
import com.green.greenfirstproject.board.dto.BoardInsertReq;
import com.green.greenfirstproject.board.model.BoardViewList;
import com.nimbusds.jose.util.Pair;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class BoardService
{
    private final BoardMapper mapper ;

    public Pair<List<BoardViewList>,Integer> selectboardList(BoardGetDto data, Integer type) throws Exception
    {
        List<BoardViewList> list = new ArrayList<>();
        if (type == 1)
            list = mapper.selectBoardListByTitle(data);
        else
            list = mapper.selectBoardListByContent(data);
         ;
        return Pair.of(list, mapper.countByTotalElements(data.getKeyword(), type));
    }

    public Long insertBoardData(BoardInsertReq data) throws Exception
    {
        mapper.insertBoardData(data);
        return data.getSeq() ;
    }

}
