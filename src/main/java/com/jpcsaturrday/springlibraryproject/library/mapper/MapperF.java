package com.jpcsaturrday.springlibraryproject.library.mapper;

import com.jpcsaturrday.springlibraryproject.library.dto.GenericFDTO;
import com.jpcsaturrday.springlibraryproject.library.model.GenericModelF;

import java.util.List;

public interface MapperF<E extends GenericModelF, D extends GenericFDTO> {

    E toEntity(D dto);

    D toDTO(E entity);

    List<E> toEntities(List<D> dtos);

    List<D> toDTOs(List<E> entities);

}
