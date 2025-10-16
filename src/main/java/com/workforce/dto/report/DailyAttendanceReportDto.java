package com.workforce.dto.report;


import com.workforce.dto.BaseDto;
import com.workforce.enums.AttendanceDailyStatus;
import com.workforce.enums.Status;
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
public class DailyAttendanceReportDto extends BaseDto {

    private UUID userId;

    private String userName;

    private LocalDate workDate;

    private String checkInTime;

    private String checkOutTime;

    private AttendanceDailyStatus status;

    private String duration;
}