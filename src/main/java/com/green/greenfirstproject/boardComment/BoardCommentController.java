package com.green.greenfirstproject.boardcomment;

import com.green.greenfirstproject.boardcomment.common.*;
import com.green.greenfirstproject.common.ResultDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.green.greenfirstproject.common.GlobalConst.SUCCESS_CODE;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/community/comment")
public class BoardCommentController {
    private final BoardCommentService service;

    @PostMapping
    ResultDto<Long> postComment(@RequestBody BoardCommentPostReq p) {
        long result = service.postBoardComment(p);

        return ResultDto.<Long> builder()
                .code(SUCCESS_CODE)
                .msg(result == 1 ? "정상처리" : "실패" )
                .data(result)
                .build();
    }
    @DeleteMapping
    ResultDto<Integer> deleteComment(@ParameterObject @ModelAttribute BoardCommentDeleteReq p){
        int result = service.delBoardComment(p);

        return ResultDto.<Integer> builder()
                .code(SUCCESS_CODE)
                .msg(result == 1 ? "정상처리" : "실패")
                .data(result)
                .build();

    }
    @PatchMapping
    ResultDto<Integer> patchComment(@ParameterObject @ModelAttribute BoardCommentPatchReq p){
        int result = service.patchBoardComment(p);

        return ResultDto.<Integer> builder()
                .code(SUCCESS_CODE)
                .msg(result == 1 ? "정상처리" : "실패")
                .data(result)
                .build();
    }
    @GetMapping
    public ResultDto<List<BoardCommentGetRes>> getComment(@RequestParam(name = "board_seq") long boardSeq) {
        List<BoardCommentGetRes> list = service.getBoardComment(boardSeq);

        return ResultDto.<List<BoardCommentGetRes>>builder()
                .code(SUCCESS_CODE)
                .msg("")
                .data(list)
                .build();

    }
}
