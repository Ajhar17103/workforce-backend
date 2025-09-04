package com.workforce.dto.attendance;


import com.workforce.dto.BaseDto;
import com.workforce.enums.AttendanceStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class AttendanceDto extends BaseDto {

    private UUID id;

    private UUID userId;

    private LocalDate workDate;

    private Long staySeconds;

    private AttendanceStatus status;

    private List<AttendanceEventDto> events;
}