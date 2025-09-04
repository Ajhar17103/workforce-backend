package com.workforce.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(force = true)
public enum AttendanceStatus {
    OPEN(1, "OPEN"),
    CLOSED(2, "CLOSED"),
    UNKNOWN(0, "UNKNOWN");

    private final Integer id;
    private String type;

    AttendanceStatus(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

    public static AttendanceStatus getById(Integer id) {
        for (AttendanceStatus attendanceStatus : AttendanceStatus.values()) {
            if(attendanceStatus.getId().equals(id)) {
                return attendanceStatus;
            }
        }
        return UNKNOWN;
    }

    public static AttendanceStatus getByType(String type) {
        for (AttendanceStatus attendanceStatus : AttendanceStatus.values()) {
            if (attendanceStatus.getType().equals(type)) {
                return attendanceStatus;
            }
        }
        return UNKNOWN;
    }

    @Converter
    public static class AttendanceStatusConverter implements AttributeConverter<AttendanceStatus, Integer> {
        @Override
        public Integer convertToDatabaseColumn(AttendanceStatus attendanceStatus) {
            return Objects.nonNull(attendanceStatus) ? attendanceStatus.getId() : UNKNOWN.id;
        }

        @Override
        public AttendanceStatus convertToEntityAttribute(Integer id) {
            return Objects.nonNull(id) ? AttendanceStatus.getById(id) : UNKNOWN;
        }
    }
}
