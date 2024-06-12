package com.green.greenfirstproject.boardLike;

import com.green.greenfirstproject.boardLike.model.FeedLike;
import com.green.greenfirstproject.common.ResultDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.green.greenfirstproject.common.GlobalConst.ERROR_CODE;
import static com.green.greenfirstproject.common.GlobalConst.SUCCESS_CODE;


@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/community/like")
@RestController
public class BoardLikeController {
    private final BoardLikeService service;

    @GetMapping
    @Operation(summary = "피드 좋아요 ")
    public ResultDto<Integer> postCommunityFavorite(@ParameterObject @ModelAttribute FeedLike p){

        try {
            int res = service.postCommunityFavorite(p);
            return ResultDto.resultDto(SUCCESS_CODE,res == 0 ? "좋아요 취소" : "좋아요 성공",res);
        }catch (Exception e){
            e.printStackTrace();
            return ResultDto.resultDto1(ERROR_CODE,"좋아요 실패");
        }
    }
}
