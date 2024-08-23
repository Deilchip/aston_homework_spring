package org.example.validator;

import lombok.experimental.UtilityClass;
import org.example.constant.ConstantsExceptionMessage;
import org.example.dto.CityDTO;

import java.security.InvalidParameterException;

/**
 * Класс для проверки данных в городах
 */
@UtilityClass
public class CityValidator {

    /**
     * Проверяет, что в объекте City изменяются только поля hasMetro и population.
     *
     * @param updated обновленный город
     * @throws InvalidParameterException если пытаются изменить что-то кроме численности и наличия метро
     */
    public static void validateUpdateFields(CityDTO updated) throws InvalidParameterException {
        if (updated.getName() != null) {
            throw new InvalidParameterException(ConstantsExceptionMessage.OTHER_FIELDS_ON_UPDATE_CITY);
        }
    }
}