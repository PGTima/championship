package com.example.championship.exceptions;

/**
 * Исключения выбрасываются при вызове метода сервиса с некорретными параметрами
 */
public class EntityIllegalArgumentException extends BaseException {
    public EntityIllegalArgumentException(String message) {
        super(message);
    }
}
