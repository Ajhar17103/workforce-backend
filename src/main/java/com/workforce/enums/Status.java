package com.workforce.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(force = true)
public enum Status {
    ACTIVE(1, "ACTIVE"),
    INACTIVE(2, "INACTIVE"),
    SUSPENDED(3, "SUSPENDED"),
    UNKNOWN(0, "UNKNOWN");

    private final Integer id;
    private String status;

    Status(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }

    public static Status getById(Integer id) {
        for (Status status : Status.values()) {
            if(status.getId().equals(id)) {
                return status;
            }
        }
        return UNKNOWN;
    }

    public static Status getByType(String type) {

        for (Status status : Status.values()) {
            if (status.getStatus().equals(type)) {
                return status;
            }
        }
        return UNKNOWN;
    }

    @Converter
    public static class StatusConverter implements AttributeConverter<Status, Integer> {
        @Override
        public Integer convertToDatabaseColumn(Status status) {
            return Objects.nonNull(status) ? status.getId() : UNKNOWN.id;
        }

        @Override
        public Status convertToEntityAttribute(Integer id) {
            return Objects.nonNull(id) ? Status.getById(id) : UNKNOWN;
        }
    }
}
