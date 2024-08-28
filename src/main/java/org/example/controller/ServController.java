package org.example.controller;

import org.example.dto.ServDTO;
import org.example.service.ServService;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * Контроллер для управления услугами.
 */
@RestController
@RequestMapping("/api/servs")
public class ServController {

    private final ServService servService;

    /**
     * Создает новый экземпляр {@link ServController} с заданным {@link ServService}.
     *
     * @param servService сервис для управления услугами
     */
    @Autowired
    public ServController(ServService servService) {
        this.servService = servService;
    }

    /**
     * Создает новую услугу.
     *
     * @param servDTO данные об услуге
     * @return созданная услуга с HTTP-статусом 201 (Created)
     * @throws InvalidParameterException если переданы некорректные параметры
     * @throws NonUniqueObjectException  если услуга с такими параметрами уже существует
     */
    @PostMapping
    public ResponseEntity<ServDTO> createServ(@RequestBody ServDTO servDTO) {
        try {
            ServDTO createdServ = servService.create(servDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdServ);
        } catch (InvalidParameterException | NonUniqueObjectException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Обновляет существующую услугу.
     *
     * @param id      идентификатор услуги
     * @param servDTO данные об услуге
     * @return обновленная услуга
     * @throws InvalidParameterException если переданы некорректные параметры
     * @throws ObjectNotFoundException   если услуга с указанным идентификатором не найдена
     * @throws NonUniqueObjectException  если услуга с такими параметрами уже существует
     */
    @PutMapping("/{id}")
    public ResponseEntity<ServDTO> updateServ(@PathVariable Long id, @RequestBody ServDTO servDTO) {
        try {
            servDTO.setId(id);
            ServDTO updatedServ = servService.update(servDTO);
            return ResponseEntity.ok(updatedServ);
        } catch (InvalidParameterException | ObjectNotFoundException | NonUniqueObjectException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Возвращает список всех услуг.
     *
     * @return список услуг
     */
    @GetMapping
    public ResponseEntity<List<ServDTO>> getAllServs() {
        List<ServDTO> servs = servService.findAll();
        return ResponseEntity.ok(servs);
    }

    /**
     * Возвращает услугу по идентификатору.
     *
     * @param id идентификатор услуги
     * @return услуга
     * @throws ObjectNotFoundException если услуга с указанным идентификатором не найдена
     */
    @GetMapping("/{id}")
    public ResponseEntity<ServDTO> getServById(@PathVariable Long id) {
        try {
            ServDTO serv = servService.findById(id);
            return ResponseEntity.ok(serv);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Удаляет услугу по идентификатору.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServ(@PathVariable Long id) {
        try {
            servService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}