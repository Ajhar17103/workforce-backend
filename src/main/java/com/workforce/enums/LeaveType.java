package com.workforce.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(force = true)
public enum LeaveType {
    SICK(1, "SICK"),
    CASUAL(2, "CASUAL"),
    ANNUAL(3, "ANNUAL"),
    PAID(3, "PAID"),
    UNKNOWN(0, "UNKNOWN");

    private final Integer id;
    private String type;

    LeaveType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

    public static LeaveType getById(Integer id) {
        for (LeaveType leaveType : LeaveType.values()) {
            if(leaveType.getId().equals(id)) {
                return leaveType;
            }
        }
        return UNKNOWN;
    }

    public static LeaveType getByType(String type) {
        for (LeaveType leaveType : LeaveType.values()) {
            if (leaveType.getType().equals(type)) {
                return leaveType;
            }
        }
        return UNKNOWN;
    }

    @Converter
    public static class LeaveTypeConverter implements AttributeConverter<LeaveType, Integer> {
        @Override
        public Integer convertToDatabaseColumn(LeaveType taskType) {
            return Objects.nonNull(taskType) ? taskType.getId() : UNKNOWN.id;
        }

        @Override
        public LeaveType convertToEntityAttribute(Integer id) {
            return Objects.nonNull(id) ? LeaveType.getById(id) : UNKNOWN;
        }
    }
}
