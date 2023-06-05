package com.jpcsaturrday.springlibraryproject.library.service;

import com.jpcsaturrday.springlibraryproject.library.dto.GenericFDTO;
import com.jpcsaturrday.springlibraryproject.library.mapper.GenericMapperF;
import com.jpcsaturrday.springlibraryproject.library.model.GenericModelF;
import com.jpcsaturrday.springlibraryproject.library.repository.GenericRepositoryF;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Абстрактный сервис который хранит в себе реализацию CRUD операций по умолчанию
 * Если реализация отличная от того что представлено в этом классе,
 * то она переопределяется в сервисе для конкретной сущности
 *
 * @param <E> - Сущность с которой мы работаем
 * @param <D> - DTO, которую мы будем отдавать/принимать дальше
 */
@Service
public abstract class GenericServiceF<E extends GenericModelF, D extends GenericFDTO> {

    protected final GenericRepositoryF<E> repository;
    protected final GenericMapperF<E, D> mapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public GenericServiceF(GenericRepositoryF<E> repository, GenericMapperF<E, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<D> listAll() {
        return mapper.toDTOs(repository.findAll());
    }

    public D getOne(final Long id) {
        return mapper.toDTO(repository.findById(id).orElseThrow(() -> new NotFoundException("Данных по заданному id: " + id + " не найдено!")));
    }

    public D create(D newObject) {
        newObject.setCreatedWhen(LocalDateTime.now());
        return mapper.toDTO(repository.save(mapper.toEntity(newObject)));
    }

    public D update(D updateObject) {
        return mapper.toDTO(repository.save(mapper.toEntity(updateObject)));
    }

    public void delete(final Long id) {
        repository.deleteById(id);
    }
}
