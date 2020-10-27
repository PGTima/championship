package com.example.championship.exceptions;

/**
 * Выбрасывается исключение, когда есть конфликты между сущностями
 */
public class EntityConflictException extends BaseException {
    public EntityConflictException(String message) {
        super(message);
    }
}
