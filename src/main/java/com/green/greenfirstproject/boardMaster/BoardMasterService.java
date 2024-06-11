package com.green.greenfirstproject.boardMaster;

import com.green.greenfirstproject.boardMaster.model.*;
import com.green.greenfirstproject.common.model.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.lang.model.util.Elements;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.green.greenfirstproject.common.GlobalConst.PAGING_SIZE;

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

    public List<BoardGetRes> getCommunityList(BoardGetReq p) {
    List<BoardGetRes> res = mapper.getCommunityList(p);
        return res;
    }

    public BoardGetRes getCommunityData(long boardSeq) {
        BoardGetRes res = mapper.getCommunityData(boardSeq);
        if(res != null){
            mapper. patchBoardHits(boardSeq);
        }
        return res;
    }
}
