package com.green.greenfirstproject.boardComment;

import com.green.greenfirstproject.boardComment.common.*;
import com.green.greenfirstproject.boardComment.common.BoardCommentGetPage;
import com.green.greenfirstproject.common.GlobalConst;
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

    public BoardCommentGetPage getBoardComment(BoardCommentGetReq data) {
        List<BoardCommentGetRes> list = mapper.getBoardComment(data);
        long totalElements = mapper.getTotalCount(data.getBoardSeq()) ;

        return new BoardCommentGetPage(list, GlobalConst.COMMENT_PAGING_SIZE, totalElements);
    }

}
