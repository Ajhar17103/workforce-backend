package com.workforce.controller;

import com.workforce.common.AbstractController;
import com.workforce.controller.api.LeaveRequestApi;
import com.workforce.dto.leave.LeaveRequestDto;
import com.workforce.param.PageableParam;
import com.workforce.param.leave.LeaveRequestParam;
import com.workforce.service.LeaveRequestService;
import com.workforce.support.ApiResponseDto;
import com.workforce.support.DeleteResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
public class LeaveRequestController extends AbstractController implements LeaveRequestApi {

    private final LeaveRequestService leaveRequestService;

    @Autowired
    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @Override
    public ResponseEntity<ApiResponseDto<LeaveRequestDto>> save(LeaveRequestParam param) throws Exception {
        return generateResponse(
                leaveRequestService.create(param),
                HttpStatus.CREATED,
                i18n("x0.has.been.saved.successfully", "leave.request")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<LeaveRequestDto>> findById(UUID id) {
        return generateResponse(
                leaveRequestService.getById(id),
                HttpStatus.OK,
                i18n("x0.get.successfully", "leave.request")
        );
    }

    @Override
    public ResponseEntity<?> findAllByUserId(UUID id) {
        return generateResponse(
                leaveRequestService.getByUserId(id),
                HttpStatus.OK,
                i18n("x0.get.successfully", "leave.request")
        );
    }

    @Override
    public ResponseEntity<?>  findAll(PageableParam pageableParam) {
        return generateResponse(
                leaveRequestService.getAll(),
                HttpStatus.OK,
                i18n("x0.get.successfully", "leave.request")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<LeaveRequestDto>> update(UUID id, LeaveRequestParam param) throws Exception {
        param.setId(id);
        return generateResponse(
                leaveRequestService.update(param),
                HttpStatus.OK,
                i18n("x0.has.been.updated.successfully", "leave.request")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<LeaveRequestDto>> statusUpdate(UUID id) throws Exception {
        return generateResponse(
                leaveRequestService.statusUpdate(id),
                HttpStatus.OK,
                i18n("x0.status.has.been.updated.successfully", "leave.request")
        );
    }

    @Override
    public ResponseEntity<DeleteResponseDto> deleteById(UUID id) throws Exception {
        leaveRequestService.delete(id);
        return generateResponse(
                HttpStatus.NO_CONTENT,
                i18n("x0.has.been.deleted.successfully", "leave.request")
        );
    }
}