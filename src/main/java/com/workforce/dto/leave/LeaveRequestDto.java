package com.workforce.dto.leave;


import com.workforce.dto.BaseDto;
import com.workforce.enums.LeaveFor;
import com.workforce.enums.LeaveStatus;
import com.workforce.enums.LeaveType;
import jakarta.persistence.Convert;
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
public class LeaveRequestDto extends BaseDto {

    private UUID userId;

    private String userName;

    private String fiscalYear;

    private LeaveType leaveType;

    private LeaveFor leaveFor;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Double totalDay;

    private String reason;

    private String attchmentPath;

    private LeaveStatus leaveStatus;

    private String remarks;
}
