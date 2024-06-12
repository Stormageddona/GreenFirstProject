package com.green.greenfirstproject.boardMaster;

import com.green.greenfirstproject.boardMaster.model.*;
import com.green.greenfirstproject.common.ResultDto;
import com.green.greenfirstproject.common.model.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.ibatis.annotations.Delete;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.green.greenfirstproject.common.GlobalConst.*;

@RestController
@RequestMapping("api/community/")
@RequiredArgsConstructor
@Slf4j
public class BoardMasterController {
    private final BoardMasterService service;

    @PostMapping
    @Operation(summary = "게시글 등록")
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
    public ResultDto<Integer> deleteCommunity(@ParameterObject @ModelAttribute  BoardDel p) {
        int res = service.deleteCommunity(p);
        return  ResultDto.resultDto1(res , res == -1 ? "삭제가 안됐습니다" : "삭제가 완료됨");
    }

    @PatchMapping
    @Operation(summary = "게시글 업데이트")
    public ResultDto<Integer> patchCommunity(@RequestBody BoardUpd p){
        try{
            int res = service.patchCommunity(p);
            return ResultDto.resultDto(SUCCESS_CODE,"게시글 수정 성공 ",res);
        }catch (CustomException e){
            e.printStackTrace();
            return ResultDto.resultDto1(ERROR_CODE,"게시글 수정 실패 ");
        }catch (Exception e ){
            e.printStackTrace();
            return ResultDto.resultDto1(ALL_ERROR,"알 수 없는 오류 ");
        }
    }
    @GetMapping("list")
    @Operation(summary =  "게시글 리스트")
    public ResultDto<BoardGetPage>getCommunityList(@ParameterObject @ModelAttribute BoardGetReq p){
        try {
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
    @Operation(summary = "게시글 디테일 ")
    public ResultDto<BoardGetRes> getCommunityData(@RequestParam(name= "boardSeq") long boardSeq){
        try {
            BoardGetRes list = service.getCommunityData(boardSeq);
            return ResultDto.resultDto(SUCCESS_CODE, "게시글 불러오기 성공",list);
        }catch (Exception e){
            return ResultDto.resultDto1(ERROR_CODE , "알수없는오류");
        }

    }

}
