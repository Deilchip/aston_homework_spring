package org.example.controller;

import org.example.dto.CityDTO;
import org.example.service.CityService;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * Контроллер для управления городами.
 */
@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * Создает новый город.
     *
     * @param cityDTO данные о городе
     * @return созданный город
     */
    @PostMapping
    public ResponseEntity<CityDTO> createCity(@RequestBody CityDTO cityDTO) {
        try {
            CityDTO createdCity = cityService.create(cityDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCity);
        } catch (InvalidParameterException | NonUniqueObjectException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Обновляет существующий город.
     *
     * @param id      идентификатор города
     * @param cityDTO данные о городе
     * @return обновленный город
     */
    @PutMapping("/{id}")
    public ResponseEntity<CityDTO> updateCity(@PathVariable Long id, @RequestBody CityDTO cityDTO) {
        try {
            cityDTO.setId(id);
            CityDTO updatedCity = cityService.update(cityDTO);
            return ResponseEntity.ok(updatedCity);
        } catch (InvalidParameterException | ObjectNotFoundException | NonUniqueObjectException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Возвращает список всех городов.
     *
     * @return список городов
     */
    @GetMapping
    public ResponseEntity<List<CityDTO>> getAllCities() {
        List<CityDTO> cities = cityService.findAll();
        return ResponseEntity.ok(cities);
    }

    /**
     * Возвращает город по идентификатору.
     *
     * @param id идентификатор города
     * @return город
     */
    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable Long id) {
        try {
            CityDTO city = cityService.findById(id);
            return ResponseEntity.ok(city);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
