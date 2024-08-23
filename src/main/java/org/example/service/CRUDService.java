package org.example.service;

import jakarta.transaction.Transactional;
import org.example.constant.ConstantsExceptionMessage;
import org.example.dto.IdentifyDTO;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс отвечающий за обобщение сервисов и реализацию их логики, содержит CRUD методы
 *
 * @param <D> параметр отвечающий за классы-dto
 * @param <E> параметр отвечающий за классы-entity
 */
@Service
public abstract class CRUDService<D extends IdentifyDTO, E, R extends JpaRepository<E, Long>> {
    /**
     * Экземпляр обобщенного класса-дто для абстракции, является основным возвращаемым объектом в классе
     */
    protected final Class<D> dtoClass;
    /**
     * Экземпляр обобщенного репозитория для абстракции
     */
    protected final R repository;

    /**
     * ModelMapper для маппинга Entity в Dto сущности и наоборот
     */
    @Autowired
    protected ModelMapper modelMapper;

    /**
     * Конструктор класса Serv.
     *
     * @param dtoClass Класс DTO.
     */
    protected CRUDService(Class<D> dtoClass, R repository) {
        this.dtoClass = dtoClass;
        this.repository = repository;
    }

    /**
     * Создает новый объект сущности на основе переданного DTO объекта.
     *
     * @param dto DTO объект для создания.
     * @return Созданный DTO объект.
     * @throws InvalidParameterException Если переданный объект является null.
     */
    @Transactional
    public abstract D create(D dto) throws InvalidParameterException, NonUniqueObjectException;

    /**
     * Удаляет объект по идентификатору.
     *
     * @param id Идентификатор объекта для удаления.
     * @throws InvalidParameterException Если объект с переданным идентификатором не существует.
     */
    @Transactional
    public void deleteById(long id) throws ObjectNotFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ObjectNotFoundException(id, ConstantsExceptionMessage.NULL_OBJECT);
        }
    }

    /**
     * Возвращает список всех DTO объектов.
     * <p>
     * return Список всех DTO объектов.
     *
     * @throws InvalidParameterException Если нет ни одного объекта.
     */
    @Transactional
    public List<D> findAll() {
        List<E> list = repository.findAll();
        return list.stream()
                .map(e -> modelMapper.map(e, dtoClass))
                .collect(Collectors.toList());
    }

    /**
     * Возвращает DTO объект с указанным идентификатором.
     *
     * @param id Идентификатор DTO объекта.
     * @return DTO объект с указанным иентификатором.
     * @throws InvalidParameterException Если DTO объект с указанным идентификатором не найден.
     */
    @Transactional
    public D findById(long id) throws ObjectNotFoundException {
        E entity = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, ConstantsExceptionMessage.NULL_OBJECT));
        return modelMapper.map(entity, dtoClass);
    }

    /**
     * Метод обновления сущностей, работает только с существующими сущностями в БД/хранилище
     *
     * @param dto экземпляр класса-dto
     * @return возвращает обновленную сущность
     * @throws InvalidParameterException прокидывается в случае невалидных полей
     */
    @Transactional
    public abstract D update(D dto) throws InvalidParameterException, ObjectNotFoundException, NonUniqueObjectException;
}
