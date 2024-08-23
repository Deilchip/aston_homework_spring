package org.example.service;

import org.example.constant.ConstantsExceptionMessage;
import org.example.dto.CityDTO;
import org.example.entity.City;
import org.example.repository.CityRepository;
import org.example.validator.CityValidator;
import org.example.validator.IdentifyValidator;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

/**
 * Сервисный класс для работы с городами.
 * Расширяет базовый класс {@link CRUDService} для выполнения основных операций CRUD.
 */
@Service
public class CityService extends CRUDService<CityDTO, City, CityRepository> {

    /**
     * Конструктор, инжектирующий репозиторий городов.
     *
     * @param repository репозиторий городов
     */
    @Autowired
    public CityService(CityRepository repository) {
        super(CityDTO.class, repository);
    }

    /**
     * Создает новый город.
     *
     * @param dto данные города
     * @return созданный город в виде DTO
     * @throws InvalidParameterException если параметры недействительны
     * @throws NonUniqueObjectException  если объект не уникален
     */
    @Override
    public CityDTO create(CityDTO dto) throws InvalidParameterException, NonUniqueObjectException {
        IdentifyValidator.validateIdOnCreate(dto.getId());
        City city = modelMapper.map(dto, City.class);
        return modelMapper.map(repository.save(city), CityDTO.class);
    }

    /**
     * Обновляет существующий город.
     *
     * @param dto данные города
     * @return обновленный город в виде DTO
     * @throws InvalidParameterException если параметры недействительны
     * @throws ObjectNotFoundException   если объект не найден
     */
    @Override
    public CityDTO update(CityDTO dto) throws InvalidParameterException, ObjectNotFoundException {
        IdentifyValidator.validateIdOnUpdate(dto.getId());
        CityValidator.validateUpdateFields(dto);
        City updatedCity = repository.findById(dto.getId())
                .orElseThrow(() -> new ObjectNotFoundException(dto.getId(), ConstantsExceptionMessage.NULL_OBJECT));
        if (updatedCity.update(dto)) {
            return modelMapper.map(repository.save(updatedCity), CityDTO.class);
        }
        return modelMapper.map(updatedCity, CityDTO.class);
    }

}
