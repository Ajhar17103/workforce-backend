package com.workforce.dto.daily_standup;


import com.workforce.dto.BaseDto;
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
public class DailyStandupDto extends BaseDto {

    private UUID userId;

    private String userName;

    private LocalDate date;

    private String description;

    private String challenge;
}
