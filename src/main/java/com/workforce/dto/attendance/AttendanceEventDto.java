package com.workforce.dto.attendance;


import com.workforce.dto.BaseDto;
import com.workforce.enums.AttendanceType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class AttendanceEventDto extends BaseDto {

    private UUID id;

    private UUID attendanceId;

    private AttendanceType type;

    private LocalDateTime timestamp;

    private Double lat;

    private Double lng;

    private String remarks;
}