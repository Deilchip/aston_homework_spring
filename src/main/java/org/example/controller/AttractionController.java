package org.example.controller;

import org.example.AttractionType;
import org.example.dto.AttractionDTO;
import org.example.service.AttractionService;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.Comparator;
import java.util.List;

/**
 * Контроллер для управления достопримечательностями.
 */
@RestController
@RequestMapping("/api/attractions")
public class AttractionController {

    private final AttractionService attractionService;

    @Autowired
    public AttractionController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    /**
     * Создает новую достопримечательность.
     *
     * @param attractionDTO данные о достопримечательности
     * @return созданная достопримечательность
     */
    @PostMapping
    public ResponseEntity<AttractionDTO> createAttraction(@RequestBody AttractionDTO attractionDTO) {
        try {
            AttractionDTO createdAttraction = attractionService.create(attractionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAttraction);
        } catch (InvalidParameterException | NonUniqueObjectException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Возвращает список всех достопримечательностей.
     *
     * @param sortBy необязательный параметр для сортировки
     * @param type   необязательный параметр для фильтрации по типу
     * @return список достопримечательностей
     */
    @GetMapping
    public ResponseEntity<List<AttractionDTO>> getAllAttractions(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) AttractionType type) {
        List<AttractionDTO> attractions;
        if (type != null) {
            attractions = attractionService.findByType(type);
            attractions = sortAttractions(attractions, sortBy);
        } else {
            attractions = sortAttractions(attractionService.findAll(), sortBy);
        }
        return ResponseEntity.ok(attractions);
    }

    /**
     * Возвращает список достопримечательностей по идентификатору города.
     *
     * @param cityId идентификатор города
     * @return список достопримечательностей
     */
    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<AttractionDTO>> getAttractionsByCityId(@PathVariable Long cityId) {
        try {
            List<AttractionDTO> attractions = attractionService.findAllByCityId(cityId);
            return ResponseEntity.ok(attractions);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Обновляет существующую достопримечательность.
     *
     * @param id            идентификатор достопримечательности
     * @param attractionDTO данные о достопримечательности
     * @return обновленная достопримечательность
     */
    @PutMapping("/{id}")
    public ResponseEntity<AttractionDTO> updateAttraction(@PathVariable Long id, @RequestBody AttractionDTO attractionDTO) {
        try {
            attractionDTO.setId(id);
            AttractionDTO updatedAttraction = attractionService.update(attractionDTO);
            return ResponseEntity.ok(updatedAttraction);
        } catch (InvalidParameterException | ObjectNotFoundException | NonUniqueObjectException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Удаляет достопримечательность по идентификатору.
     *
     * @param id идентификатор достопримечательности
     * @return HTTP-статус 204 (No Content) в случае успешного удаления
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttraction(@PathVariable Long id) {
        try {
            attractionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private List<AttractionDTO> sortAttractions(List<AttractionDTO> attractions, String sortBy) {
        if (sortBy != null) {
            switch (sortBy) {
                case "1":
                    attractions.sort(Comparator.comparing(AttractionDTO::getName));
                    break;
                case "2":
                    attractions.sort(Comparator.comparing(AttractionDTO::getName).reversed());
                    break;
                default:
                    break;
            }
        }
        return attractions;
    }
}