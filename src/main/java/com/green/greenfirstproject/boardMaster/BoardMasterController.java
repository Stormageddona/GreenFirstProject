package com.green.greenfirstproject.boardMaster;

import com.green.greenfirstproject.boardComment.BoardCommentService;
import com.green.greenfirstproject.boardComment.common.BoardCommentGetReq;
import com.green.greenfirstproject.boardMaster.model.*;
import com.green.greenfirstproject.common.ResultDto;
import com.green.greenfirstproject.common.model.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import static com.green.greenfirstproject.common.GlobalConst.*;

@RestController
@Tag(name = "커뮤니티 관리", description = "커뮤니티관련 CRUD")
@RequestMapping("api/community/")
@RequiredArgsConstructor
@Slf4j
public class BoardMasterController {
    private final BoardMasterService service;
    private final BoardCommentService commentService;

    @PostMapping
    @Operation(summary = "게시글 등록")
    @ApiResponse(description = "1: 성공 -2: 제목이없음 -1 : 실패  ")
    public ResultDto<Long> postCommunity(@RequestBody BoardReq p) {
        try {
            long res = service.postCommunity(p);
            return ResultDto.resultDto(SUCCESS_CODE, "게시글 등록 ",res);
        }catch (CustomException e){
            e.printStackTrace();
            return ResultDto.resultDto1(ERROR_CODE,"제목이 없습니다");
        }catch (Exception e){
            e.printStackTrace();
            return ResultDto.resultDto1(ALL_ERROR,"알수없는오류");
        }
    }
    @DeleteMapping
    @Operation(summary = "게시글 삭제")
    @ApiResponse(description = "1: 성공  -1 : 실패  ")
    public ResultDto<Integer> deleteCommunity(@ParameterObject @ModelAttribute  BoardDel p) {
        int res = service.deleteCommunity(p);
        return  ResultDto.resultDto1(res , res == -1 ? "삭제가 안됐습니다" : "삭제가 완료됨");
    }

    @PatchMapping
    @Operation(summary = "게시글 업데이트")
    @ApiResponse(description = "1: 성공 -2: 게시글 수정실패 -1 : 실패  ")
    public ResultDto<Integer> patchCommunity(@RequestBody BoardUpd p){
        try{
            int res = service.patchCommunity(p);
            return ResultDto.resultDto(SUCCESS_CODE,"게시글 수정 성공 ",res);
        }catch (CustomException e){
            e.printStackTrace();
            return ResultDto.resultDto1(ALL_ERROR,"게시글 수정 실패 ");
        }catch (Exception e ){
            e.printStackTrace();
            return ResultDto.resultDto1(ERROR_CODE,"알 수 없는 오류 ");
        }
    }
    @GetMapping("list")
    @Operation(summary =  "게시글 리스트")
    @ApiResponse(description = "1: 성공 -1 : 실패  ")
    public ResultDto<BoardGetPage>getCommunityList(@ParameterObject @ModelAttribute BoardGetReq p){
        try {
            //order 값 벗어날시 강제로 3 으로 설정
            if (p.getOrder() < 1 || p.getOrder() > 3) {p.setOrder(3);}
            Pair<BoardGetPage,Integer> data = service.getCommunityList(p);
            BoardGetPage list = data.getLeft() ;
            Integer totalElements = data.getRight();
            Integer totalPage = (totalElements + PAGING_SIZE - 1) / PAGING_SIZE ;
            list.setTotalPage(totalPage);
            list.setTotalElements(totalElements);
            return  ResultDto.resultDto(SUCCESS_CODE,"페이징성공",list);
        }catch (Exception e){
            e.printStackTrace();
            return ResultDto.resultDto1(ERROR_CODE,"페이징실패");
        }
    }
    @GetMapping("detail")
    @Operation(summary = "게시글 디테일")
    @ApiResponse(description = "1: 성공 -2: 게시글없음 -1 : 실패  ")
    public ResultDto<BoardGetResDetail> getCommunityData(@RequestParam(name= "boardSeq") long boardSeq){
        try {
            BoardGetResDetail list = service.getCommunityData(boardSeq);
            list.setCommentList(commentService.getBoardComment(new BoardCommentGetReq(boardSeq,1,COMMENT_PAGING_SIZE)));
            return ResultDto.resultDto(SUCCESS_CODE, "게시글 불러오기 성공",list);
        }catch (NullPointerException e) {
            return ResultDto.resultDto1(-2, "게시글이 없습니다");
        } catch (Exception e){
            return ResultDto.resultDto1(ERROR_CODE , "알수없는오류");
        }

    }

}
