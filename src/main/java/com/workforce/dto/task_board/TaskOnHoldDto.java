package com.workforce.dto.task_board;


import com.workforce.dto.BaseDto;
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
public class TaskOnHoldDto extends BaseDto {

    private UUID taskId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}


