package com.workforce.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(force = true)
public enum MenuType {
    MAIN(1, "MAIN"),
    SUBMENU(2, "SUB"),
    UNKNOWN(0, "UNKNOWN");

    private final Integer id;
    private String type;

    MenuType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

    public static MenuType getById(Integer id) {
        for (MenuType menuType : MenuType.values()) {
            if(menuType.getId().equals(id)) {
                return menuType;
            }
        }
        return UNKNOWN;
    }

    public static MenuType getByType(String type) {

        for (MenuType menuType : MenuType.values()) {
            if (menuType.getType().equals(type)) {
                return menuType;
            }
        }
        return UNKNOWN;
    }

    @Converter
    public static class ModuleTypeConverter implements AttributeConverter<MenuType, Integer> {
        @Override
        public Integer convertToDatabaseColumn(MenuType menuType) {
            return Objects.nonNull(menuType) ? menuType.getId() : UNKNOWN.id;
        }

        @Override
        public MenuType convertToEntityAttribute(Integer id) {
            return Objects.nonNull(id) ? MenuType.getById(id) : UNKNOWN;
        }
    }
}
