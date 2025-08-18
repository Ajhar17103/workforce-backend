package com.workforce.param.master;


import com.workforce.enums.BloodGroup;
import com.workforce.param.BaseParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserParam extends BaseParam {

    @Schema(hidden = true)
    private UUID id;

    @NotNull(message = "x0.is.required")
    @Schema(example = "Asif Mahmud", description = "Name of the user")
    private String name;

    @NotNull(message = "x0.is.required")
    @Schema(example = "e8b5a51a-4c3a-11ec-81d3-0242ac130003", description = "Designation Id of the user")
    private UUID designationId;

    @NotNull(message = "x0.is.required")
    @Schema(example = "e8b5a51a-4c3a-11ec-81d3-0242ac130003", description = "Role Id of the user")
    private UUID roleId;

    @NotNull(message = "x0.is.required")
    @Schema(example = "1998-08-08", description = "Date of Birth of the user")
    private LocalDate dob;

    @NotNull(message = "x0.is.required")
    @Schema(example = "test@gmail.com", description = "Email of the user")
    private String email;

    @NotNull(message = "x0.is.required")
    @Schema(example = "01700000000", description = "Phone of the user")
    private String phone;

    @NotNull(message = "x0.is.required")
    @Schema(example = "H#33,R#15, S#12, Uttara-1230", description = "Current Address of the user")
    private String currentAddress;

    @NotNull(message = "x0.is.required")
    @Schema(example = "H#33,R#15, S#12, Uttara-1230", description = "Present Address of the user")
    private String presentAddress;

    @NotNull(message = "x0.is.required")
    @Schema(example = "O+", description = "Blood Group of the user")
    private BloodGroup bloodGroup;

    @NotNull(message = "x0.is.required")
    @Schema(description = "Profile Icon of the user")
    private String profileIcon;

    @NotNull(message = "x0.is.required")
    @Schema(example = "inc@rrEc1",description = "Password of the user")
    private String password;
}