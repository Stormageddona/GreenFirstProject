package com.green.greenfirstproject.schedule.management;

import com.green.greenfirstproject.common.GlobalConst;
import com.green.greenfirstproject.common.dto.ResultDto;
import com.green.greenfirstproject.common.exception.DataNotFoundException;
import com.green.greenfirstproject.common.page.ResponseDTO;
import com.green.greenfirstproject.common.page.ResponseDTO2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.green.greenfirstproject.schedule.management.model.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/schedule/plant/management")
@Tag(name = "식물일정등록", description = "식물일정등록 CRUD")
public class ScheduleManagementController {
    private final ScheduleManagementService service;

    @PostMapping
    @Operation(summary = "식물일정등록", description = "식물일정등록 Post")
    public ResultDto<Integer> insScheduleManagement(@RequestBody ScheduleManagementPostReq p) {
        try {
            int result = service.insScheduleManagement(p);
            return ResultDto.<Integer>builder().
                    code(1).
                    msg("등록에 성공했습니다.").
                    data(result).
                    build();
        } catch (DataNotFoundException e){
            e.printStackTrace();
            return ResultDto.<Integer>builder().code(-1).msg("등록에 실패했습니다").build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDto.<Integer>builder().code(-2).msg("오류").build();
        }
    }

    @DeleteMapping
    @Operation(summary = "식물일정등록 삭제", description = "식물일정등록 Delete")
    public ResultDto<Integer> delScheduleManagement(@ParameterObject @ModelAttribute ScheduleManagementDeleteReq p) {
        try {
            int result = service.delScheduleManagement(p);
            return ResultDto.<Integer>builder().
                    code(1).
                    msg("삭제에 성공했습니다." ).
                    data(result).
                    build();
        } catch (DataNotFoundException e){
            e.printStackTrace();
            return ResultDto.<Integer>builder().code(-1).msg("삭제에 실패했습니다").build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDto.<Integer>builder().code(-2).msg("오류").build();
        }
    }

    @PatchMapping
    @Operation(summary = "식물일정등록 수정", description = "식물일정등록 Patch")
    public ResultDto<Integer> updScheduleManagement(@RequestBody ScheduleManagementPatchReq p) {
        try {
            int result = service.updateScheduleManagement(p);
            return ResultDto.<Integer>builder().
                    code(1).
                    msg("수정에 성공했습니다.").
                    data(result).
                    build();
        } catch (DataNotFoundException e){
            e.printStackTrace();
            return ResultDto.<Integer>builder().code(-1).msg("수정에 실패했습니다").build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDto.<Integer>builder().code(-1).msg("오류").build();
        }
    }

    @GetMapping("/month")
    @Operation(summary = "식물일정 월 단위 조회", description = "식물일정조회 MonthGet")
    public ResultDto<List<ScheduleManagementGetMonthRes>> getScheduleManagementMonth(@ParameterObject @ModelAttribute
                                                                                     ScheduleManagementGetMonthReq p){
        try {
            List<ScheduleManagementGetMonthRes> result = service.getScheduleManagementMonth(p);

            return ResultDto.<List<ScheduleManagementGetMonthRes>>builder().
                    code(1).
                    msg(HttpStatus.OK.toString()).
                    data(result).
                    build();
        } catch (Exception e){
            e.printStackTrace();
            return ResultDto.<List<ScheduleManagementGetMonthRes>>builder().code(-1).msg("조회 오류").build();
        }
    }
    @GetMapping("/day")
    @Operation(summary = "식물일정 일 단위 조회", description = "식물일정조회 DayGet")
    public ResultDto<ResponseDTO2> selScheduleManagementDay(@ParameterObject @ModelAttribute ScheduleManagementGetDayReq p){
        try {
            log.info("p: {}", p.getManagementDate());
            ResponseDTO2 dto = service.selScheduleManagementDay(p);

            return ResultDto.<ResponseDTO2>builder().
                    code(1).
                    msg(HttpStatus.OK.toString()).
                    data(dto).
                    build();
        } catch (Exception e){
            e.printStackTrace();
            return ResultDto.<ResponseDTO2>builder().code(-1).msg("조회 오류").build();
        }
    }

    @GetMapping("/detail")
    @Operation(summary = "식물일정 상세 조회", description = "식물일정조회 DayDetailGet")
    public ResultDto<ScheduleManagementGetDayDetailRes> selScheduleManagementDetail(@ParameterObject @ModelAttribute ScheduleManagementGetDayDetailReq p){
        try {
            ScheduleManagementGetDayDetailRes result = service.selScheduleManagementDetail(p);

            return ResultDto.<ScheduleManagementGetDayDetailRes>builder().
                    code(1).
                    msg(HttpStatus.OK.toString()).
                    data(result).
                    build();
        } catch (Exception e){
            e.printStackTrace();
            return ResultDto.<ScheduleManagementGetDayDetailRes>builder().code(-1).msg("조회 오류").build();
        }
    }
}