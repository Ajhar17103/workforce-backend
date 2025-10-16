package com.workforce.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(force = true)
public enum AttendanceDailyStatus {
    REGULAR(1, "REGULAR"),
    LATE(2, "LATE"),
    ON_LEAVE(2, "ON_LEAVE"),
    ABSENT(2, "ABSENT"),
    UNKNOWN(0, "UNKNOWN");

    private final Integer id;
    private String type;

    AttendanceDailyStatus(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

    public static AttendanceDailyStatus getById(Integer id) {
        for (AttendanceDailyStatus attendanceDailyStatus : AttendanceDailyStatus.values()) {
            if(attendanceDailyStatus.getId().equals(id)) {
                return attendanceDailyStatus;
            }
        }
        return UNKNOWN;
    }

    public static AttendanceDailyStatus getByType(String type) {
        for (AttendanceDailyStatus attendanceDailyStatus : AttendanceDailyStatus.values()) {
            if (attendanceDailyStatus.getType().equals(type)) {
                return attendanceDailyStatus;
            }
        }
        return UNKNOWN;
    }

    @Converter
    public static class AttendanceDailyStatusConverter implements AttributeConverter<AttendanceDailyStatus, Integer> {
        @Override
        public Integer convertToDatabaseColumn(AttendanceDailyStatus attendanceStatus) {
            return Objects.nonNull(attendanceStatus) ? attendanceStatus.getId() : UNKNOWN.id;
        }

        @Override
        public AttendanceDailyStatus convertToEntityAttribute(Integer id) {
            return Objects.nonNull(id) ? AttendanceDailyStatus.getById(id) : UNKNOWN;
        }
    }
}
