package com.workforce.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
public enum Priority {
    LOW(1, "LOW"),
    NORMAL(2, "NORMAL"),
    HIGH(3, "HIGH"),
    URGENT(4, "URGENT"),
    IMMEDIATE(5, "IMMEDIATE"),
    UNKNOWN(0, "Unknown");

    private final Integer id;
    private final String name;

    Priority(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    private static final Map<Integer, Priority> BY_ID = new HashMap<>();
    private static final Map<String, Priority> BY_NAME = new HashMap<>();

    static {
        for (Priority e : values()) {
            BY_ID.put(e.id, e);
            BY_NAME.put(e.name.toUpperCase(), e);
        }
    }

    public static Priority getById(Integer id) {
        return BY_ID.getOrDefault(id, UNKNOWN);
    }

    public static Priority getByName(String name) {
        return name == null ? UNKNOWN : BY_NAME.getOrDefault(name.toUpperCase(), UNKNOWN);
    }

    @Converter(autoApply = true)
    public static class PriorityConverter implements AttributeConverter<Priority, Integer> {
        @Override
        public Integer convertToDatabaseColumn(Priority priority) {
            return Objects.nonNull(priority) ? priority.getId() : UNKNOWN.id;
        }

        @Override
        public Priority convertToEntityAttribute(Integer id) {
            return Objects.nonNull(id) ? Priority.getById(id) : UNKNOWN;
        }
    }
}

