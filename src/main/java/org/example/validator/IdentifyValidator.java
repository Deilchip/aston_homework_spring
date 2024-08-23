package org.example.validator;

import lombok.experimental.UtilityClass;
import org.example.constant.ConstantsExceptionMessage;
import org.example.constant.ConstantsValidator;

import java.security.InvalidParameterException;

/**
 * Класс для проверки id в создаваемых/обновляемых объектах
 */
@UtilityClass
public class IdentifyValidator {
    /**
     * метод на отсутствие id в методах create
     *
     * @param id id создаваемого объекта на проверку
     * @throws InvalidParameterException выкидывает ошибку в случае если id равен чему-либо
     */
    public static void validateIdOnCreate(Long id) throws InvalidParameterException {
        if (id != null) {
            throw new InvalidParameterException(ConstantsExceptionMessage.ID_ON_CREATE);
        }
    }

    /**
     * метод на наличие id в методах update
     *
     * @param id id обновляемого объекта на проверку
     * @throws InvalidParameterException выкидывает ошибку в случае если id не равен чему-либо
     */
    public static void validateIdOnUpdate(Long id) throws InvalidParameterException {
        if (id == null || id <= ConstantsValidator.ZERO) {
            throw new InvalidParameterException(ConstantsExceptionMessage.ID_ON_UPDATE);
        }
    }
}
