package com.workforce.dto.master;

import com.workforce.dto.BaseDto;
import com.workforce.enums.BloodGroup;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Lob;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseDto {
    private String name;

    private UUID departmentId;

    private String departmentName;

    private UUID designationId;

    private String designationName;

    private UUID roleId;

    private String roleName;

    private LocalDate dob;

    private String email;

    private String phone;

    private String currentAddress;

    private String presentAddress;

    private BloodGroup bloodGroup;

    private String profileIcon;

    private String password;
}
