package org.example.service;

import org.example.AttractionType;
import org.example.constant.ConstantsExceptionMessage;
import org.example.dto.AttractionDTO;
import org.example.entity.Attraction;
import org.example.repository.AttractionRepository;
import org.example.validator.AttractionValidator;
import org.example.validator.IdentifyValidator;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервисный класс для работы с достопримечательностями.
 * Расширяет базовый класс {@link CRUDService} для выполнения основных операций CRUD.
 */
@Service
public class AttractionService extends CRUDService<AttractionDTO, Attraction, AttractionRepository> {

    /**
     * Конструктор, инжектирующий репозиторий достопримечательностей.
     *
     * @param repository репозиторий достопримечательностей
     */
    @Autowired
    public AttractionService(AttractionRepository repository) {
        super(AttractionDTO.class, repository);
    }

    /**
     * Создает новую достопримечательность.
     *
     * @param dto данные достопримечательности
     * @return созданная достопримечательность в виде DTO
     * @throws InvalidParameterException если параметры недействительны
     * @throws NonUniqueObjectException  если объект не уникален
     */
    @Override
    public AttractionDTO create(AttractionDTO dto) throws InvalidParameterException {
        IdentifyValidator.validateIdOnCreate(dto.getId());
        Attraction attraction = modelMapper.map(dto, Attraction.class);
        return modelMapper.map(repository.save(attraction), AttractionDTO.class);
    }

    /**
     * Обновляет существующую достопримечательность.
     *
     * @param dto данные достопримечательности
     * @return обновленная достопримечательность в виде DTO
     * @throws InvalidParameterException если параметры недействительны
     * @throws ObjectNotFoundException   если объект не найден
     * @throws NonUniqueObjectException  если объект не уникален
     */
    @Override
    public AttractionDTO update(AttractionDTO dto) throws InvalidParameterException, ObjectNotFoundException {
        IdentifyValidator.validateIdOnUpdate(dto.getId());
        AttractionValidator.validateUpdateFields(dto);
        Attraction updatedAttraction = repository.findById(dto.getId())
                .orElseThrow(() -> new ObjectNotFoundException(dto.getId(), ConstantsExceptionMessage.NULL_OBJECT));
        if (updatedAttraction.update(dto)) {
            return modelMapper.map(repository.save(updatedAttraction), AttractionDTO.class);
        }
        return modelMapper.map(updatedAttraction, AttractionDTO.class);
    }

    /**
     * Находит список достопримечательностей по типу.
     *
     * @param type тип достопримечательности
     * @return список достопримечательностей в виде DTO
     * @throws ObjectNotFoundException если объекты не найдены
     */
    public List<AttractionDTO> findByType(AttractionType type) {
        Optional<List<Attraction>> optionalAttractions = repository.findByType(type);
        return checkOptionalAttractions(optionalAttractions);
    }

    public List<AttractionDTO> findAllByCityId(long cityId) throws ObjectNotFoundException {
        Optional<List<Attraction>> optionalAttractions = repository.findByCityId(cityId);
        return checkOptionalAttractions(optionalAttractions);
    }

    private List<AttractionDTO> checkOptionalAttractions(Optional<List<Attraction>> optionalAttractions) {
        if (optionalAttractions.isPresent()) {
            return optionalAttractions.get().stream()
                    .map(a -> modelMapper.map(a, AttractionDTO.class))
                    .collect(Collectors.toList());
        } else {
            throw new ObjectNotFoundException(optionalAttractions, ConstantsExceptionMessage.NULL_OBJECT);
        }
    }
}