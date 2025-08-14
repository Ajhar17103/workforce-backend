package com.workforce.annotation.validators;

import com.workforce.annotation.CheckUnique;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class CheckUniqueValidator implements ConstraintValidator<CheckUnique, Object> {

    private String entityName;
    private String columnName;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void initialize(CheckUnique constraintAnnotation) {
        this.entityName = constraintAnnotation.entityName();
        this.columnName = constraintAnnotation.columnName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            String requestMethod = request.getMethod();

            if ("PUT".equals(requestMethod) || "PATCH".equals(requestMethod)) {
                String pathInfo = request.getRequestURI();
                String uuidRegex = "/([^/]+)/([\\w\\d-]{36})";
                Pattern pattern = Pattern.compile(uuidRegex);
                Matcher matcher = pattern.matcher(pathInfo);

                if (matcher.find()) {
                    String pathIdString = matcher.group(2);
                    UUID pathId = UUID.fromString(pathIdString);

                    Object existingEntity = entityManager.createQuery(
                                    "SELECT e FROM " + entityName + " e WHERE " + columnName + " = :value", Object.class)
                            .setParameter("value", value)
                            .getSingleResult();

                    UUID existingEntityId = null;
                    try {
                        Method getIdMethod = existingEntity.getClass().getMethod("getId");
                        existingEntityId = (UUID) getIdMethod.invoke(existingEntity);
                        log.error("existingEntityId: " + existingEntityId);

                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        log.error("Error getting id property from existingEntity: {}", e.getMessage());
                    }

                    if (!Objects.equals(existingEntityId, pathId)) {
                        return buildViolationMessage(context, value);
                    }
                }
            } else if ("POST".equals(requestMethod)) {
                return buildViolationMessage(context, value);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return true;

    }

    private boolean buildViolationMessage(ConstraintValidatorContext context, Object value) {
        if (!isUnique(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Value already exists!").addConstraintViolation();
            return false;
        }
        return true;
    }

    private boolean isUnique(Object value) {
        try {
            Long count = entityManager.createQuery(
                            "SELECT COUNT(e) FROM " + entityName + " e WHERE " + columnName + " = :value", Long.class)
                    .setParameter("value", value)
                    .getSingleResult();

            return count == 0;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
