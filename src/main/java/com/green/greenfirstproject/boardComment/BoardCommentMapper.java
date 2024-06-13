package com.green.greenfirstproject.boardComment;

import com.green.greenfirstproject.boardComment.common.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardCommentMapper {
    int delBoardComment(BoardCommentDeleteReq p);
    long postBoardComment(BoardCommentPostReq p);
    List<BoardCommentGetRes> getBoardComment(BoardCommentGetReq data);
    int patchBoardComment(BoardCommentPatchReq p);
    long getTotalCount(long boardSeq) ;

}
