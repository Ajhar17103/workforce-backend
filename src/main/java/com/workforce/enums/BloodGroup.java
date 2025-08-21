package com.workforce.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(force = true)
public enum BloodGroup {
    A_POSITIVE(1, "A+"),
    A_NEGATIVE(2, "A-"),
    B_POSITIVE(3, "B+"),
    B_NEGATIVE(4, "B-"),
    O_POSITIVE(5, "O+"),
    O_NEGATIVE(6, "O-"),
    AB_POSITIVE(7, "AB+"),
    AB_NEGATIVE(8, "AB-"),
    BOMBAY_Oh(9, "BOMBAY_Oh"),
    UNKNOWN(0, "UNKNOWN");

    private final Integer id;
    private final String type;

    BloodGroup(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public static BloodGroup getById(Integer id){
        for (BloodGroup bloodGroup : BloodGroup.values()) {
            if (bloodGroup.getId().equals(id)) {
                return bloodGroup;
            }
        }
        return UNKNOWN;
    }

    public static BloodGroup getByType(String type){
        for (BloodGroup bloodGroup : BloodGroup.values()) {
            if (bloodGroup.getType().equals(type)) {
                return bloodGroup;
            }
        }
        return UNKNOWN;
    }

    @Converter
    public static class BloodGroupConverter implements AttributeConverter<BloodGroup,Integer> {

        @Override
        public Integer convertToDatabaseColumn(BloodGroup attribute) {
            return Objects.nonNull(attribute) ? attribute.getId() : UNKNOWN.getId();
        }

        @Override
        public BloodGroup convertToEntityAttribute(Integer id){
            return Objects.nonNull(id) ? BloodGroup.getById(id) : UNKNOWN;
        }
    }
}
