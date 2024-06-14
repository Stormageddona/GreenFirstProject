package com.green.greenfirstproject.boardComment;

import com.green.greenfirstproject.boardComment.common.*;
import com.green.greenfirstproject.common.ResultDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.green.greenfirstproject.common.GlobalConst.COMMENT_PAGING_SIZE;
import static com.green.greenfirstproject.common.GlobalConst.SUCCESS_CODE;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/community/comment")
@Tag(name = "댓글 컨트롤러")
public class BoardCommentController {
    private final BoardCommentService service;

    @PostMapping
    @Operation(summary = "댓글 추가 기능")
    ResultDto<Long> postComment(@RequestBody BoardCommentPostReq p) {
        long result = service.postBoardComment(p);

        return ResultDto.<Long> builder()
                .code(SUCCESS_CODE)
                .msg(result == 1 ? "정상처리" : "실패" )
                .data(result)
                .build();
    }
    @DeleteMapping
    @Operation(summary = "댓글 삭제")
    ResultDto<Integer> deleteComment(@ParameterObject @ModelAttribute BoardCommentDeleteReq p){
        int result = service.delBoardComment(p);

        return ResultDto.<Integer> builder()
                .code(SUCCESS_CODE)
                .msg(result == 1 ? "정상처리" : "실패")
                .data(result)
                .build();

    }
    @PatchMapping
    @Operation(summary = "댓글 수정")
    ResultDto<Integer> patchComment(@ParameterObject @ModelAttribute BoardCommentPatchReq p){
        int result = service.patchBoardComment(p);

        return ResultDto.<Integer> builder()
                .code(SUCCESS_CODE)
                .msg(result == 1 ? "정상처리" : "실패")
                .data(result)
                .build();
    }
    @GetMapping
    @Operation(summary = "댓글 조회(페이지 변경)")
    public ResultDto<BoardCommentGetPage> getComment(@RequestParam(name = "board_seq") long boardSeq, Integer page) {
        BoardCommentGetReq data = new BoardCommentGetReq(boardSeq, page, COMMENT_PAGING_SIZE) ;
        BoardCommentGetPage list = service.getBoardComment(data);

        return ResultDto.<BoardCommentGetPage>builder()
                .code(SUCCESS_CODE)
                .msg("정상 처리 되었습니다.")
                .data(list)
                .build();

    }
}
