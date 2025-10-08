package com.workforce.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(force = true)
public enum LeaveFor {
    FULL_DAY(1, "FULL_DAY"),
    HALF_DAY(2, "HALF_DAY"),
    UNKNOWN(0, "UNKNOWN");

    private final Integer id;
    private String type;

    LeaveFor(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

    public static LeaveFor getById(Integer id) {
        for (LeaveFor leaveFor : LeaveFor.values()) {
            if(leaveFor.getId().equals(id)) {
                return leaveFor;
            }
        }
        return UNKNOWN;
    }

    public static LeaveFor getByType(String type) {
        for (LeaveFor leaveFor : LeaveFor.values()) {
            if (leaveFor.getType().equals(type)) {
                return leaveFor;
            }
        }
        return UNKNOWN;
    }

    @Converter
    public static class LeaveTypeConverter implements AttributeConverter<LeaveFor, Integer> {
        @Override
        public Integer convertToDatabaseColumn(LeaveFor taskType) {
            return Objects.nonNull(taskType) ? taskType.getId() : UNKNOWN.id;
        }

        @Override
        public LeaveFor convertToEntityAttribute(Integer id) {
            return Objects.nonNull(id) ? LeaveFor.getById(id) : UNKNOWN;
        }
    }
}
