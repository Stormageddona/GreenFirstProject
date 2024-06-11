package com.green.greenfirstproject.boardMaster;

import com.green.greenfirstproject.boardMaster.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMasterMapper {
    int postCommunity(BoardReq p);

    int deleteCommunity(BoardDel p);

    int patchCommunity(BoardUpd p);

    List<BoardGetRes> getCommunityList(BoardGetReq p);

    BoardGetRes getCommunityData(long boardSeq);

    int patchBoardHits(long boardSeq);

//    List<BoardGetRes> getCommunityListTitle(BoardGetReq p);
//
//    List<BoardGetRes> getCommunityListContent(BoardGetReq p);
//
//    List<BoardGetRes> getCommunityListWriter(BoardGetReq p);
}
