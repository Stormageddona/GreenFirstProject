package com.green.greenfirstproject.boardcomment;

import com.green.greenfirstproject.boardcomment.common.BoardCommentDeleteReq;
import com.green.greenfirstproject.boardcomment.common.BoardCommentGetRes;
import com.green.greenfirstproject.boardcomment.common.BoardCommentPatchReq;
import com.green.greenfirstproject.boardcomment.common.BoardCommentPostReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardCommentMapper {
    int delBoardComment(BoardCommentDeleteReq p);
    long postBoardComment(BoardCommentPostReq p);
    List<BoardCommentGetRes> getBoardComment(long boardSeq);
    int patchBoardComment(BoardCommentPatchReq p);

}
