package com.workforce.controller;

import java.util.UUID;
import com.workforce.common.AbstractController;
import com.workforce.controller.api.MenuApi;
import com.workforce.dto.master.MenuDto;
import com.workforce.param.PageableParam;
import com.workforce.param.master.MenuParam;
import com.workforce.service.MenuService;
import com.workforce.support.ApiResponseDto;
import com.workforce.support.DeleteResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@CrossOrigin("*")
public class MenuController extends AbstractController implements MenuApi {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public ResponseEntity<ApiResponseDto<MenuDto>> save(MenuParam param) throws Exception {
        return generateResponse(
                menuService.create(param),
                HttpStatus.CREATED,
                i18n("x0.has.been.saved.successfully", "menu")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<MenuDto>> findById(UUID id) {
        return generateResponse(
                menuService.getById(id),
                HttpStatus.OK,
                i18n("x0.get.successfully", "menu")
        );
    }

    @Override
    public ResponseEntity<?> findAll(PageableParam pageableParam) {
//        if (pageableParam.isPageable()) {
//            PageRequest pageRequest = PageRequest.of(
//                    pageableParam.getPage(),
//                    pageableParam.getSize(),
//                    Sort.by(pageableParam.getSortDirection(), pageableParam.getSortBy())
//            );
//            return generateResponse(
//                    menuService.getAll(pageRequest),
//                    HttpStatus.OK,
//                    i18n("x0.get.successfully", "menus")
//            );
//        } else {
            Sort sort = Sort.by(Sort.Direction.ASC, "name"); // default sorting, adjust as needed
            return generateResponse(
                    menuService.getAll(sort),
                    HttpStatus.OK,
                    i18n("x0.get.successfully", "menus")
            );
//        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<MenuDto>> update(UUID id, MenuParam param) throws Exception {
        param.setId(id);
        return generateResponse(
                menuService.update(param),
                HttpStatus.OK,
                i18n("x0.has.been.updated.successfully", "menu")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<MenuDto>> statusUpdate(UUID id) throws Exception {
        return generateResponse(
                menuService.statusUpdate(id),
                HttpStatus.OK,
                i18n("x0.status.has.been.updated.successfully", "menu")
        );
    }

    @Override
    public ResponseEntity<DeleteResponseDto> deleteById(UUID id) throws Exception {
        menuService.delete(id);
        return generateResponse(
                HttpStatus.NO_CONTENT,
                i18n("x0.has.been.deleted.successfully", "menu")
        );
    }
}
