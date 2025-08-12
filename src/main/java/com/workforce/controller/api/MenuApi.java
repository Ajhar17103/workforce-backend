package com.workforce.controller.api;


import java.util.UUID;
import jakarta.validation.Valid;
import com.workforce.common.api.*;
import com.workforce.constant.ApiPath;
import com.workforce.dto.master.MenuDto;
import com.workforce.entity.master.Menu;
import com.workforce.param.PageableParam;
import org.springframework.http.HttpStatus;
import com.workforce.param.master.MenuParam;
import com.workforce.support.ApiResponseDto;
import com.workforce.support.DeleteResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(ApiPath.Menu.ROOT_PATH)
public interface MenuApi extends GetApi<MenuDto>, GetAllApi<Menu>, CreateApi<MenuDto, MenuParam>, UpdateApi<MenuDto, MenuParam>, StatusUpdateApi<MenuDto>, DeleteApi {

    @Operation(summary = "Create menu")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Menu created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @Override
    default ResponseEntity<ApiResponseDto<MenuDto>> save(@RequestBody @Valid MenuParam param) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Find menu by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Menu found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Menu not found", content = @Content(schema = @Schema(implementation = MenuDto.class)))
    })
    @GetMapping(value = ApiPath.Menu.MENU_IDENTIFIER)  // e.g., "/{id}"
    @Override
    default ResponseEntity<ApiResponseDto<MenuDto>> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get all menus")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Menus retrieved successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "No menus found", content = @Content(schema = @Schema(implementation = MenuDto.class)))
    })
    @GetMapping
    @Override
    default ResponseEntity<?> findAll(PageableParam pageable) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Update menu")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Menu updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Menu not found", content = @Content(schema = @Schema(implementation = MenuDto.class)))
    })
    @PutMapping(value = ApiPath.Menu.MENU_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<MenuDto>> update(@PathVariable UUID id, @RequestBody @Valid MenuParam param) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Update menu status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Menu status updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Menu not found", content = @Content(schema = @Schema(implementation = MenuDto.class)))
    })
    @PatchMapping(value = ApiPath.Menu.MENU_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<MenuDto>> statusUpdate(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Delete menu")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Menu deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Menu not found")
    })
    @DeleteMapping(value = ApiPath.Menu.MENU_IDENTIFIER)
    @Override
    default ResponseEntity<DeleteResponseDto> deleteById(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

