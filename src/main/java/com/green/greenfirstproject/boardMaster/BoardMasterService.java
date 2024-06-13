package com.green.greenfirstproject.boardMaster;

import com.green.greenfirstproject.boardMaster.model.*;
import com.green.greenfirstproject.common.model.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardMasterService {
    private  final  BoardMasterMapper mapper;

    public long postCommunity(BoardReq p){
        System.out.println(p);
        int res = mapper.postCommunity(p);
        if(p.getTitle() == null){
            throw  new CustomException("제목이 없습니다");
        }
        return p.getBoardSeq();
    }

    public int deleteCommunity(BoardDel p){
        int res = mapper.deleteCommunity(p);
        if(res == 0) {
            return -1;
        }
        return res;
    }
    public int patchCommunity(BoardUpd p){
        int res = mapper.patchCommunity(p);
        if(res == 0){
            throw new CustomException("게시글을 수정 할 수 없습니다");
        }
        return res;
    }

    public Pair<BoardGetPage,Integer> getCommunityList(BoardGetReq p) {
        List<BoardGetResList> res1 = mapper.getCommunityList(p);
        BoardGetPage res2 = new BoardGetPage();
        res2.setList(res1);
        Integer totalElements = mapper.totalCount(p.getKeyword()) ;

        return Pair.of(res2,totalElements);
    }

    public BoardGetResDetail getCommunityData(long boardSeq) {
        BoardGetResDetail res = mapper.getCommunityData(boardSeq);
        System.out.println(res);
        if(res != null){
            mapper.patchBoardHits(res.getBoardSeq());
        }else {
            throw  new NullPointerException();
        }
        return res;
    }
}
