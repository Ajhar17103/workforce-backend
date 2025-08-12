package com.workforce.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@SuperBuilder(toBuilder = true)
public class BaseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -6802067245636533901L;
    private UUID id;
    private Long version;
    private Boolean deleted;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private String ipAddress;
}
