package org.example.service;

import org.example.constant.ConstantsExceptionMessage;
import org.example.dto.ServDTO;
import org.example.entity.Serv;
import org.example.repository.ServRepository;
import org.example.validator.IdentifyValidator;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

/**
 * Сервисный класс для работы с услугами.
 * Расширяет базовый класс {@link CRUDService} для выполнения основных операций CRUD.
 */
@Service
public class ServService extends CRUDService<ServDTO, Serv, ServRepository> {

    /**
     * Конструктор, инжектирующий репозиторий услуг.
     *
     * @param repository репозиторий услуг
     */
    @Autowired
    public ServService(ServRepository repository) {
        super(ServDTO.class, repository);
    }

    /**
     * Создает новую услугу.
     *
     * @param dto данные услуги
     * @return созданная услуга в виде DTO
     * @throws InvalidParameterException если параметры недействительны
     * @throws NonUniqueObjectException  если объект не уникален
     */
    @Override
    public ServDTO create(ServDTO dto) throws InvalidParameterException, NonUniqueObjectException {
        IdentifyValidator.validateIdOnCreate(dto.getId());
        Serv serv = modelMapper.map(dto, Serv.class);
        return modelMapper.map(repository.save(serv), ServDTO.class);
    }

    /**
     * Обновляет существующую услугу.
     *
     * @param dto данные услуги
     * @return обновленная услуга в виде DTO
     * @throws InvalidParameterException если параметры недействительны
     * @throws ObjectNotFoundException   если объект не найден
     * @throws NonUniqueObjectException  если объект не уникален
     */
    @Override
    public ServDTO update(ServDTO dto) throws InvalidParameterException, ObjectNotFoundException, NonUniqueObjectException {
        IdentifyValidator.validateIdOnUpdate(dto.getId());
        Serv updatedServ = repository.findById(dto.getId())
                .orElseThrow(() -> new ObjectNotFoundException(dto.getId(), ConstantsExceptionMessage.NULL_OBJECT));
        if (updatedServ.update(dto)) {
            return modelMapper.map(repository.save(updatedServ), ServDTO.class);
        }
        return modelMapper.map(updatedServ, ServDTO.class);
    }
}
