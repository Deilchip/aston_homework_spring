package org.example.validator;

import lombok.experimental.UtilityClass;
import org.example.constant.ConstantsExceptionMessage;
import org.example.dto.AttractionDTO;

import java.security.InvalidParameterException;

/**
 * Класс для проверки данных в достопримечательностях
 */
@UtilityClass
public class AttractionValidator {

    /**
     * Проверяет, что в объекте Attraction изменяются только поля description и type.
     *
     * @param updated обновленная достопримечательность
     * @throws InvalidParameterException если пытаются изменить что-то кроме описания и типа
     */
    public static void validateUpdateFields(AttractionDTO updated) throws InvalidParameterException {
        if (updated.getName() != null || updated.getCreationDate() != null) {
            throw new InvalidParameterException(ConstantsExceptionMessage.OTHER_FIELDS_ON_UPDATE_ATTRACTION);
        }
    }
}