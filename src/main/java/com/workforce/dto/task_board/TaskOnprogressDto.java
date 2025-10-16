package com.workforce.dto.task_board;


import com.workforce.dto.BaseDto;
import com.workforce.entity.task_board.Task;
import com.workforce.enums.TaskStatus;
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
public class TaskOnprogressDto extends BaseDto {

    private UUID taskId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}


