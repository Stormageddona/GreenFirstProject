package com.green.greenfirstproject.boardMaster;

import com.green.greenfirstproject.boardMaster.model.BoardDel;
import com.green.greenfirstproject.boardMaster.model.BoardReq;
import com.green.greenfirstproject.boardMaster.model.BoardUpd;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMasterMapper {
    int postCommunity(BoardReq p);

    int deleteCommunity(BoardDel p);

    int patchCommunity(BoardUpd p);




}
