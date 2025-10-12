package com.workforce.controller;

import com.workforce.common.AbstractController;
import com.workforce.controller.api.AllocatedLeaveApi;
import com.workforce.dto.leave.AllocatedLeaveDto;
import com.workforce.param.PageableParam;
import com.workforce.param.leave.AllocatedLeaveParam;
import com.workforce.service.AllocatedLeaveService;
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
public class AllocatedLeaveController extends AbstractController implements AllocatedLeaveApi {

    private final AllocatedLeaveService allocatedLeaveService;

    @Autowired
    public AllocatedLeaveController(AllocatedLeaveService allocatedLeaveService) {
        this.allocatedLeaveService = allocatedLeaveService;
    }

    @Override
    public ResponseEntity<ApiResponseDto<AllocatedLeaveDto>> save(AllocatedLeaveParam param) throws Exception {
        return generateResponse(
                allocatedLeaveService.create(param),
                HttpStatus.CREATED,
                i18n("x0.has.been.saved.successfully", "allocated.leave")
        );
    }

    public ResponseEntity<ListResponseDto<AllocatedLeaveDto>> saveAllocateLeaveForAllUser(String year) throws Exception {
        return generateResponse(
                allocatedLeaveService.allocateLeaveForAllUsers(year),
                HttpStatus.CREATED,
                i18n("x0.has.been.saved.successfully", "allocated.leaves")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<AllocatedLeaveDto>> findById(UUID id) {
        return generateResponse(
                allocatedLeaveService.getById(id),
                HttpStatus.OK,
                i18n("x0.get.successfully", "allocated.leave")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<AllocatedLeaveDto>> findAllByUserId(UUID id) {
        return generateResponse(
                allocatedLeaveService.getByUserId(id),
                HttpStatus.OK,
                i18n("x0.get.successfully", "allocated.leave")
        );
    }

    @Override
    public ResponseEntity<?> findAll(PageableParam pageableParam) {
        return generateResponse(
                allocatedLeaveService.getAll(),
                HttpStatus.OK,
                i18n("x0.get.successfully", "allocated.leave")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<AllocatedLeaveDto>> update(UUID id, AllocatedLeaveParam param) throws Exception {
        param.setId(id);
        return generateResponse(
                allocatedLeaveService.update(param),
                HttpStatus.OK,
                i18n("x0.has.been.updated.successfully", "allocated.leave")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<AllocatedLeaveDto>> statusUpdate(UUID id) throws Exception {
        return generateResponse(
                allocatedLeaveService.statusUpdate(id),
                HttpStatus.OK,
                i18n("x0.status.has.been.updated.successfully", "allocated.leave")
        );
    }

    @Override
    public ResponseEntity<DeleteResponseDto> deleteById(UUID id) throws Exception {
        allocatedLeaveService.delete(id);
        return generateResponse(
                HttpStatus.NO_CONTENT,
                i18n("x0.has.been.deleted.successfully", "allocated.leave")
        );
    }
}