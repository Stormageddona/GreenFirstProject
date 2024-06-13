package com.green.greenfirstproject.boardcomment;

import com.green.greenfirstproject.boardcomment.common.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class BoardCommentService {
    private final BoardCommentMapper mapper;

    public long postBoardComment(BoardCommentPostReq p) {

        long res = mapper.postBoardComment(p);

        return res;
    }

    public int delBoardComment(BoardCommentDeleteReq p) {
        int res = mapper.delBoardComment(p);
        if(res == 0 ) {
            return -1;
        }
        return res;

    }
    public int patchBoardComment(BoardCommentPatchReq p) {
        int res = mapper.patchBoardComment(p);
        if(res == 0) {
            return -1;
        }
        return res;
    }

    public List<BoardCommentGetRes> getBoardComment(long boardSeq) {
        List<BoardCommentGetRes> list = mapper.getBoardComment(boardSeq);
        return list;
    }

}
