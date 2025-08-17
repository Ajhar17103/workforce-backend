package com.workforce.dto.master;

import com.workforce.dto.BaseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class DepartmentDto extends BaseDto {
    private String name;
}
