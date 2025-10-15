package com.workforce.dto.report;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
public class UserSummaryDto {

    private UUID id;

    private String name;

    private UUID departmentId;

    private String departmentName;

    private UUID designationId;

    private String designationName;

    private UUID roleId;

    private String roleName;
}