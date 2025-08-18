package com.workforce.dto.master;


import com.workforce.dto.BaseDto;
import com.workforce.enums.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class DesignationDto extends BaseDto {

    private String departmentId;

    private String name;
}
