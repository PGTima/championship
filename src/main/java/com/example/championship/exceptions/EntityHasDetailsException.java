package com.example.championship.exceptions;

import org.springframework.util.Assert;

/**
 *  Выбрасываются исключения если происходит удаление главного элемента, а на него есть ссылки
 */
public class EntityHasDetailsException extends BaseException{
    public EntityHasDetailsException(String message) {
        super(message);
    }
    public EntityHasDetailsException(String type, Object id){
        this(formatMessage(type, id));
    }

    private static String formatMessage(String type, Object id){
        Assert.hasText(type, "Тип не может быть пустым");
        Assert.notNull(type, "Идентификатор объекта не может быть null");
        Assert.hasText(id.toString(), "Идентификатор не может быть пустым");
        return String.format("%s ссылается на  удаляемый объект с идентификатором %s не найден", type, id);
    }
}
