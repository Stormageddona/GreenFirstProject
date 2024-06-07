package com.green.greenfirstproject.boardMaster;

import com.green.greenfirstproject.boardMaster.model.BoardDel;
import com.green.greenfirstproject.boardMaster.model.BoardReq;
import com.green.greenfirstproject.boardMaster.model.BoardRes;
import com.green.greenfirstproject.boardMaster.model.BoardUpd;
import com.green.greenfirstproject.common.model.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardMasterService {
    private  final  BoardMasterMapper mapper;

    public long postCommunity(BoardReq p){
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
}
