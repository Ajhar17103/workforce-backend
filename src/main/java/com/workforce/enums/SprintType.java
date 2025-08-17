package com.workforce.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(force = true)
public enum SprintType {
    PLANNED(1, "PLANNED"),
    ACTIVE(2, "ACTIVE"),
    COMPLETED(2, "COMPLETED"),
    UNKNOWN(0, "UNKNOWN");

    private final Integer id;
    private String type;

    SprintType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

    public static SprintType getById(Integer id) {
        for (SprintType sprintType : SprintType.values()) {
            if(sprintType.getId().equals(id)) {
                return sprintType;
            }
        }
        return UNKNOWN;
    }

    public static SprintType getByType(String type) {
        for (SprintType sprintType : SprintType.values()) {
            if (sprintType.getType().equals(type)) {
                return sprintType;
            }
        }
        return UNKNOWN;
    }

    @Converter
    public static class SprintTypeConverter implements AttributeConverter<SprintType, Integer> {
        @Override
        public Integer convertToDatabaseColumn(SprintType sprintType) {
            return Objects.nonNull(sprintType) ? sprintType.getId() : UNKNOWN.id;
        }

        @Override
        public SprintType convertToEntityAttribute(Integer id) {
            return Objects.nonNull(id) ? SprintType.getById(id) : UNKNOWN;
        }
    }
}
