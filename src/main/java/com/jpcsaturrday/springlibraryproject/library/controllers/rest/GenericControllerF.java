package com.jpcsaturrday.springlibraryproject.library.controllers.rest;

import com.jpcsaturrday.springlibraryproject.library.dto.GenericFDTO;
import com.jpcsaturrday.springlibraryproject.library.model.GenericModelF;
import com.jpcsaturrday.springlibraryproject.library.service.GenericServiceF;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@Slf4j
public abstract class GenericControllerF<E extends GenericModelF, D extends GenericFDTO> {

    protected GenericServiceF<E, D> service;

    public GenericControllerF(GenericServiceF<E, D> service) {
        this.service = service;
    }

    @Operation(description = "Получить запись по ID", method = "getOneById")
    @RequestMapping(value = "/getOneById",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<D> getOneById(@RequestParam(value = "id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getOne(id));
    }

    @Operation(description = "Получить все записи", method = "getAll")
    @RequestMapping(value = "/getAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<D>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.listAll());
    }

    @Operation(description = "Создать запись", method = "add")
    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<D> create(@RequestBody D newEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(newEntity));
    }

    @Operation(description = "Обновить запись", method = "update")
    @RequestMapping(value = "/update",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<D> update(@RequestBody D updatedEntity,
                                    @RequestParam(value = "id") Long id) {
        updatedEntity.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(updatedEntity));
    }

    @Operation(description = "Удалить запись", method = "delete")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
    }
}

/*@RestController
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
}*/
