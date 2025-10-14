package com.workforce.controller;

import com.workforce.common.AbstractController;
import com.workforce.controller.api.DailyStandupApi;
import com.workforce.dto.daily_standup.DailyStandupDto;
import com.workforce.param.PageableParam;
import com.workforce.param.daily_standup.DailyStandupParam;
import com.workforce.service.DailyStandupService;
import com.workforce.support.ApiResponseDto;
import com.workforce.support.DeleteResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@RestController
public class DailyStandupController extends AbstractController implements DailyStandupApi {

    private final DailyStandupService dailyStandupService;

    @Autowired
    public DailyStandupController(DailyStandupService dailyStandupService) {
        this.dailyStandupService = dailyStandupService;
    }

    @Override
    public ResponseEntity<ApiResponseDto<DailyStandupDto>> save(DailyStandupParam param) throws Exception {
        return generateResponse(
                dailyStandupService.create(param),
                HttpStatus.CREATED,
                i18n("x0.has.been.saved.successfully", "leave.request")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<DailyStandupDto>> findById(UUID id) {
        return generateResponse(
                dailyStandupService.getById(id),
                HttpStatus.OK,
                i18n("x0.get.successfully", "leave.request")
        );
    }

    @Override
    public ResponseEntity<?>  findAll(PageableParam pageableParam) {
        return generateResponse(
                dailyStandupService.getAll(),
                HttpStatus.OK,
                i18n("x0.get.successfully", "leave.request")
        );
    }

    @Override
    public ResponseEntity<?> findAllByUserId(UUID id) {
        return generateResponse(
                dailyStandupService.getByUserId(id),
                HttpStatus.OK,
                i18n("x0.get.successfully", "leave.request")
        );
    }

    @Override
    public ResponseEntity<?> findAllByToDate(LocalDate date) {
        return generateResponse(
                dailyStandupService.getByToDate(date),
                HttpStatus.OK,
                i18n("x0.get.successfully", "leave.request")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<DailyStandupDto>> update(UUID id, DailyStandupParam param) throws Exception {
        param.setId(id);
        return generateResponse(
                dailyStandupService.update(param),
                HttpStatus.OK,
                i18n("x0.has.been.updated.successfully", "leave.request")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<DailyStandupDto>> statusUpdate(UUID id) throws Exception {
        return generateResponse(
                dailyStandupService.statusUpdate(id),
                HttpStatus.OK,
                i18n("x0.status.has.been.updated.successfully", "leave.request")
        );
    }

    @Override
    public ResponseEntity<DeleteResponseDto> deleteById(UUID id) throws Exception {
        dailyStandupService.delete(id);
        return generateResponse(
                HttpStatus.NO_CONTENT,
                i18n("x0.has.been.deleted.successfully", "leave.request")
        );
    }
}