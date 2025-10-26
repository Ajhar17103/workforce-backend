package com.workforce.controller.api;


import com.workforce.common.api.*;
import com.workforce.constant.ApiPath;
import com.workforce.dto.master.UserDto;
import com.workforce.entity.master.User;
import com.workforce.param.PageableParam;
import com.workforce.param.master.UserParam;
import com.workforce.support.ApiResponseDto;
import com.workforce.support.DeleteResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(ApiPath.User.ROOT_PATH)
public interface UserApi extends GetApi<UserDto>, GetAllApi<User>, CreateApi<UserDto, UserParam>, UpdateApi<UserDto, UserParam>, StatusUpdateApi<UserDto>, DeleteApi {

    @Operation(summary = "Create User")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @Override
    default ResponseEntity<ApiResponseDto<UserDto>> save(@RequestBody @Valid UserParam param) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Find user by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = UserDto.class)))
    })
    @GetMapping(value = ApiPath.User.USER_IDENTIFIER)  // e.g., "/{id}"
    @Override
    default ResponseEntity<ApiResponseDto<UserDto>> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get all users")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "users retrieved successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "No users found", content = @Content(schema = @Schema(implementation = UserDto.class)))
    })
    @GetMapping
    @Override
    default ResponseEntity<?> findAll(@Schema(hidden = true) PageableParam pageable) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Update user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = UserDto.class)))
    })
    @PutMapping(value = ApiPath.User.USER_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<UserDto>> update(@PathVariable UUID id, @RequestBody @Valid UserParam param) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Update user status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User  status updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "User  not found", content = @Content(schema = @Schema(implementation = UserDto.class)))
    })
    @PatchMapping(value = ApiPath.User.USER_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<UserDto>> statusUpdate(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Delete user")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping(value = ApiPath.User.USER_IDENTIFIER)
    @Override
    default ResponseEntity<DeleteResponseDto> deleteById(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

