package com.workforce.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(force = true)
public enum TaskType {
    PLANNED(1, "PLANNED"),
    UNPLANNED(2, "UNPLANNED"),
    UNKNOWN(0, "UNKNOWN");

    private final Integer id;
    private String type;

    TaskType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

    public static TaskType getById(Integer id) {
        for (TaskType taskType : TaskType.values()) {
            if(taskType.getId().equals(id)) {
                return taskType;
            }
        }
        return UNKNOWN;
    }

    public static TaskType getByType(String type) {
        for (TaskType taskType : TaskType.values()) {
            if (taskType.getType().equals(type)) {
                return taskType;
            }
        }
        return UNKNOWN;
    }

    @Converter
    public static class TaskTypeConverter implements AttributeConverter<TaskType, Integer> {
        @Override
        public Integer convertToDatabaseColumn(TaskType taskType) {
            return Objects.nonNull(taskType) ? taskType.getId() : UNKNOWN.id;
        }

        @Override
        public TaskType convertToEntityAttribute(Integer id) {
            return Objects.nonNull(id) ? TaskType.getById(id) : UNKNOWN;
        }
    }
}
