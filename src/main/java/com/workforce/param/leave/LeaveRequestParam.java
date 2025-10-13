package com.workforce.param.leave;


import com.workforce.enums.LeaveFor;
import com.workforce.enums.LeaveStatus;
import com.workforce.enums.LeaveType;
import com.workforce.param.BaseParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LeaveRequestParam extends BaseParam {

    @Schema(hidden = true)
    private UUID id;

    @NotNull(message = "x0.is.required")
    @Schema(example = "e8b5a51a-4c3a-11ec-81d3-0242ac130003", description = "Description of the leave")
    private UUID userId;

    @NotNull(message = "x0.is.required")
    @Schema(example = "2025", description = "Fiscal Year of the leave")
    private String fiscalYear;

    @NotNull(message = "x0.is.required")
    @Schema(example = "SICK", description = "Leave Type of the leave", allowableValues = {"SICK", "CASUAL", "ANNUAL","PAID"})
    private LeaveType leaveType;

    @NotNull(message = "x0.is.required")
    @Schema(example = "FULL_DAY", description = "Leave For of the leave", allowableValues = {"FULL_DAY", "HALF_DAY"})
    private LeaveFor leaveFor;

    @NotNull(message = "x0.is.required")
    @Schema(example = "2025-10-12", description = "From Date of the leave")
    private LocalDate fromDate;

    @NotNull(message = "x0.is.required")
    @Schema(example = "2025-10-12", description = "To Date of the leave")
    private LocalDate toDate;

    @NotNull(message = "x0.is.required")
    @Schema(example = "back pain and injuries received in accidents", description = "Reason of the leave")
    private String reason;


    @NotNull(message = "x0.is.required")
    @Schema(example = "b2fc8ac9480164fef9b2818b7c686c1726966ee2ae1e5584286fbb498f59cad.png", description = "Attchment Path of the leave")
    private String attchmentPath;

    @Schema(hidden = true, example = "PENDING", description = "Leave Status of the leave", allowableValues = {"PENDING", "APPROVED", "REJECTED","CANCELLED"})
    private LeaveStatus leaveStatus;

    @Schema(hidden = true, example = "leave not approved", description = "Remarks of the leave")
    private String remarks;
}