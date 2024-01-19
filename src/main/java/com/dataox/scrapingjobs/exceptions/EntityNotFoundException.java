package com.dataox.scrapingjobs.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class<?> entityClass, String description) {
        super(String.format("Entity of type '%s' %s not found in the database",
                entityClass.getSimpleName(), description));
    }
}
