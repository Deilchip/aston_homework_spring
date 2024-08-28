package org.example.constant;

/**
 * Класс, содержащий константы для сообщений об исключениях.
 */
public class ConstantsExceptionMessage {

    /**
     * Сообщение об ошибке, когда объект не существует.
     */
    public static final String NULL_OBJECT = "Объект не существует";

    /**
     * Сообщение об ошибке, когда при создании объекта указан id.
     */
    public static final String ID_ON_CREATE = "Нельзя создать объект с id";

    /**
     * Сообщение об ошибке, когда при обновлении объекта не указан id.
     */
    public static final String ID_ON_UPDATE = "Нельзя обновить объект без указания id";
    public static final String OTHER_FIELDS_ON_UPDATE_ATTRACTION = "Можно изменять только описание и тип достопримечательности";
    public static final String OTHER_FIELDS_ON_UPDATE_CITY = "Можно изменять только численность населения и наличия метро в городе";

}