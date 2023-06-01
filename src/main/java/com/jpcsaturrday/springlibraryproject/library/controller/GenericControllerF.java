package com.jpcsaturrday.springlibraryproject.library.controller;

import com.jpcsaturrday.springlibraryproject.library.model.GenericModelF;
import com.jpcsaturrday.springlibraryproject.library.repository.GenericRepositoryF;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
public abstract class GenericControllerF<T extends GenericModelF> {

    private final GenericRepositoryF<T> genericRepositoryF;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public GenericControllerF(GenericRepositoryF<T> genericRepositoryF) {
        this.genericRepositoryF = genericRepositoryF;
    }

    @Operation(description = "Получить запись по ID", method = "getOneById")
    @RequestMapping(value = "/getOneById",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> getOneById(@RequestParam(value = "id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(genericRepositoryF.findById(id).orElseThrow(() -> new NotFoundException("Данные по ID не найдены!")));
    }

    @Operation(description = "Получить все записи", method = "getAll")
    @RequestMapping(value = "/getAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(genericRepositoryF.findAll());
    }

    @Operation(description = "Создать Запись", method = "add")
    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> create(@RequestBody T newEntity) {
        log.info(newEntity.toString());
        newEntity.setCreatedWhen(LocalDateTime.now());
        genericRepositoryF.save(newEntity);
        log.info(newEntity.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(newEntity);
    }

    @Operation(description = "Обновить запись", method = "update")
    @RequestMapping(value = "/update",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> update(@RequestBody T updatedEntity,
                                    @RequestParam(value = "id") Long id) {
        updatedEntity.setId(id);
        genericRepositoryF.save(updatedEntity);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedEntity);
    }

    @Operation(description = "Удалить запись", method = "delete")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") Long id) {
        genericRepositoryF.deleteById(id);
    }
}
