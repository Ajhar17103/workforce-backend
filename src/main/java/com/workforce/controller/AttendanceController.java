package com.workforce.controller;

import com.workforce.common.AbstractController;
import com.workforce.controller.api.AttendanceApi;
import com.workforce.dto.attendance.AttendanceDto;
import com.workforce.param.PageableParam;
import com.workforce.param.attendance.AttendanceParam;
import com.workforce.service.AttendanceService;
import com.workforce.support.ApiResponseDto;
import com.workforce.support.DeleteResponseDto;
import com.workforce.support.ListResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@Slf4j
@RestController
public class AttendanceController extends AbstractController implements AttendanceApi {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @Override
    public ResponseEntity<ApiResponseDto<AttendanceDto>> save(AttendanceParam param) throws Exception {
        return generateResponse(
                attendanceService.create(param),
                HttpStatus.CREATED,
                i18n("x0.has.been.saved.successfully", "attendance")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<AttendanceDto>> findById(UUID id) {
        return generateResponse(
                attendanceService.getById(id),
                HttpStatus.OK,
                i18n("x0.get.successfully", "attendance")
        );
    }

    @Override
    public ResponseEntity<ListResponseDto<AttendanceDto>> findAllByUserId(UUID id) {
        return generateResponse(
                attendanceService.getAttendanceByUserId(id),
                HttpStatus.OK,
                i18n("x0.get.successfully", "attendances")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<AttendanceDto>> findByUserIdAndWorkDate(AttendanceParam param) {
        return generateResponse(
                attendanceService.getAttendanceByUserIdAndWorkDate(param),
                HttpStatus.OK,
                i18n("x0.get.successfully", "attendances")
        );
    }

    @Override
    public ResponseEntity<?> findAll(PageableParam pageableParam) {
        return generateResponse(
                attendanceService.getAll(),
                HttpStatus.OK,
                i18n("x0.get.successfully", "attendances")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<AttendanceDto>> update(UUID id, AttendanceParam param) throws Exception {
        param.setId(id);
        return generateResponse(
                attendanceService.update(param),
                HttpStatus.OK,
                i18n("x0.has.been.updated.successfully", "attendance")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<AttendanceDto>> statusUpdate(UUID id) throws Exception {
        return generateResponse(
                attendanceService.statusUpdate(id),
                HttpStatus.OK,
                i18n("x0.status.has.been.updated.successfully", "attendance")
        );
    }

    @Override
    public ResponseEntity<DeleteResponseDto> deleteById(UUID id) throws Exception {
        attendanceService.delete(id);
        return generateResponse(
                HttpStatus.NO_CONTENT,
                i18n("x0.has.been.deleted.successfully", "attendance")
        );
    }
}