package com.green.greenfirstproject.board;

import com.green.greenfirstproject.auth.principal.PrincipalUtil;
import com.green.greenfirstproject.board.dto.BoardGetDto;
import com.green.greenfirstproject.board.dto.BoardInsertReq;
import com.green.greenfirstproject.board.model.BoardViewList;
import com.green.greenfirstproject.common.dto.Result;
import com.green.greenfirstproject.common.dto.ResultDto;
import com.green.greenfirstproject.common.dto.ResultDtoList;
import com.green.greenfirstproject.common.dto.ResultError;
import com.nimbusds.jose.util.Pair;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/community")
public class BoardRestController
{
    private final BoardService service ;
    private static final Integer PAGE_SIZE = 10 ;


    @GetMapping("list")
    @Operation(summary = "커뮤니티 리스트 조회" , description = "키워드로 게시판 리스트 조회")
    @ApiResponse(description =
            "<p>ResponseCode 응답 코드 </p> " +
                    "<p>  1 : 정상 </p> " +
                    "<p> -1 : 실패(의도하지 않은 오류)</p>"
    )
    public Result getCommunityList(@RequestParam @Nullable String keyword,@RequestParam @Nullable Integer page,
                                   @Parameter(description =
                                           "<p> 1 : 제목으로 검색 </p>" +
                                           "<p> 2 : 내용으로 검색 </p>")
                                   @RequestParam @Nullable Integer type)
    {
        BoardGetDto dto = new BoardGetDto(keyword,page, PAGE_SIZE) ;
        if (type == null) type = 1 ;

        List<BoardViewList> list = null ;
        int totalElements = 0 ;
        try {
            Pair<List<BoardViewList>, Integer> data =  service.selectboardList(dto,type) ;
            list = data.getLeft() ;
            totalElements = data.getRight() ;
        } catch (Exception e) {
            log.error("An error occurred: ", e);
            return ResultError.builder().build();
        }

        return ResultDtoList.<BoardViewList>builder().data(list).totalElements(totalElements).PAGE_SIZE(PAGE_SIZE).build();
    }

    @PostMapping
    @Operation(summary = "게시글 추가", description = "게시글을 추가하는 기능.")
    @ApiResponse(description =
            "<p>ResponseCode 응답 코드 </p> " +
                    "<p>  1 : 정상 </p> " +
                    "<p> -1 : 실패(의도하지 않은 오류)</p>"
    )
    public Result postBoardData(@RequestBody BoardInsertReq data)
    {
        PrincipalUtil.getPrincipal().getUser() ;
        try {

            Long seq = service.insertBoardData(data) ;

            return ResultDto.<Long>builder().Data(seq).build();
        } catch (Exception e) {
            log.error("An error occurred: ", e);
            return ResultError.builder().build();
        }
    }


}
