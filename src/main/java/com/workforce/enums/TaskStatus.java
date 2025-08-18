package com.workforce.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(force = true)
public enum TaskStatus {
    TO_DO(1, "TO_DO"),
    IN_PROGRESS(2, "IN_PROGRESS"),
    COMPLETED(3, "COMPLETED"),
    UNKNOWN(0, "UNKNOWN");

    private final Integer id;
    private String type;

    TaskStatus(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

    public static TaskStatus getById(Integer id) {
        for (TaskStatus taskStatus : TaskStatus.values()) {
            if(taskStatus.getId().equals(id)) {
                return taskStatus;
            }
        }
        return UNKNOWN;
    }

    public static TaskStatus getByType(String type) {
        for (TaskStatus taskStatus : TaskStatus.values()) {
            if (taskStatus.getType().equals(type)) {
                return taskStatus;
            }
        }
        return UNKNOWN;
    }

    @Converter
    public static class TaskTypeConverter implements AttributeConverter<TaskStatus, Integer> {
        @Override
        public Integer convertToDatabaseColumn(TaskStatus taskStatus) {
            return Objects.nonNull(taskStatus) ? taskStatus.getId() : UNKNOWN.id;
        }

        @Override
        public TaskStatus convertToEntityAttribute(Integer id) {
            return Objects.nonNull(id) ? TaskStatus.getById(id) : UNKNOWN;
        }
    }
}
