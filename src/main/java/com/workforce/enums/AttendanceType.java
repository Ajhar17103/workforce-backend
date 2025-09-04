package com.workforce.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(force = true)
public enum AttendanceType {
    DAY_START(1, "DAY_START"),
    DAY_END(2, "DAY_END"),
    BREAK_START(3, "BREAK_START"),
    BREAK_END(4, "BREAK_END"),
    OUTING_START(5, "OUTING_START"),
    OUTING_END(6, "OUTING_END"),
    UNKNOWN(0, "UNKNOWN");

    private final Integer id;
    private String type;

    AttendanceType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

    public static AttendanceType getById(Integer id) {
        for (AttendanceType attendanceType : AttendanceType.values()) {
            if(attendanceType.getId().equals(id)) {
                return attendanceType;
            }
        }
        return UNKNOWN;
    }

    public static AttendanceType getByType(String type) {
        for (AttendanceType attendanceType : AttendanceType.values()) {
            if (attendanceType.getType().equals(type)) {
                return attendanceType;
            }
        }
        return UNKNOWN;
    }

    @Converter
    public static class AttendanceTypeConverter implements AttributeConverter<AttendanceType, Integer> {
        @Override
        public Integer convertToDatabaseColumn(AttendanceType attendanceType) {
            return Objects.nonNull(attendanceType) ? attendanceType.getId() : UNKNOWN.id;
        }

        @Override
        public AttendanceType convertToEntityAttribute(Integer id) {
            return Objects.nonNull(id) ? AttendanceType.getById(id) : UNKNOWN;
        }
    }
}
