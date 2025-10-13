package com.workforce.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(force = true)
public enum LeaveStatus {
    PENDING(1, "PENDING"),
    APPROVED(2, "APPROVED"),
    REJECTED(3, "REJECTED"),
    CANCELLED(4, "CANCELLED"),
    UNKNOWN(0, "UNKNOWN");

    private final Integer id;
    private String type;

    LeaveStatus(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

    public static LeaveStatus getById(Integer id) {
        for (LeaveStatus leaveFor : LeaveStatus.values()) {
            if(leaveFor.getId().equals(id)) {
                return leaveFor;
            }
        }
        return UNKNOWN;
    }

    public static LeaveStatus getByType(String type) {
        for (LeaveStatus leaveFor : LeaveStatus.values()) {
            if (leaveFor.getType().equals(type)) {
                return leaveFor;
            }
        }
        return UNKNOWN;
    }

    @Converter
    public static class LeaveStatusConverter implements AttributeConverter<LeaveStatus, Integer> {
        @Override
        public Integer convertToDatabaseColumn(LeaveStatus taskType) {
            return Objects.nonNull(taskType) ? taskType.getId() : UNKNOWN.id;
        }

        @Override
        public LeaveStatus convertToEntityAttribute(Integer id) {
            return Objects.nonNull(id) ? LeaveStatus.getById(id) : UNKNOWN;
        }
    }
}
