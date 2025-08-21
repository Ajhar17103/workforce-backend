package com.workforce.enums;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(force = true)
public enum TaskTracker {
    BUG(1, "BUG"),
    SPRINT(2, "SPRINT"),
    BACKLOG(3, "BACKLOG"),
    BUG_FIX(3, "BUG_FIX"),
    DOCUMENTATION(4, "DOCUMENTATION"),
    CLIENT_MEETING(5, "CLIENT_MEETING"),
    PROJECT_PLANNING(6, "PROJECT_PLANNING"),
    PROJECT_MEETING(7, "PROJECT_MEETING"),
    TESTING_BUG_REPORTING(8, "TESTING_BUG REPORTING"),
    NEW_DEVELOPMENT(9, "NEW_DEVELOPMENT"),
    REQUIREMENT_ANALYSIS(10, "REQUIREMENT_ANALYSIS"),
    UNKNOWN(0, "UNKNOWN");

    private final Integer id;
    private final String type;

    TaskTracker(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public static TaskTracker getById(Integer id){
        for (TaskTracker taskTracker : TaskTracker.values()) {
            if (taskTracker.getId().equals(id)) {
                return taskTracker;
            }
        }
        return UNKNOWN;
    }

    public static TaskTracker getByTracker(String type){
        for (TaskTracker taskTracker : TaskTracker.values()) {
            if (taskTracker.getType().equals(type)) {
                return taskTracker;
            }
        }
        return UNKNOWN;
    }

    @Converter
    public static class TaskTrackerConverter implements AttributeConverter<TaskTracker,Integer> {

        @Override
        public Integer convertToDatabaseColumn(TaskTracker attribute) {
            return Objects.nonNull(attribute) ? attribute.getId() : UNKNOWN.getId();
        }

        @Override
        public TaskTracker convertToEntityAttribute(Integer id){
            return Objects.nonNull(id) ? TaskTracker.getById(id) : UNKNOWN;
        }
    }
}

