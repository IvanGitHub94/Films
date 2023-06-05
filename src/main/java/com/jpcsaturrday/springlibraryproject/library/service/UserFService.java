package com.jpcsaturrday.springlibraryproject.library.service;

import com.jpcsaturrday.springlibraryproject.library.dto.RoleFDTO;
import com.jpcsaturrday.springlibraryproject.library.dto.UserFDTO;
import com.jpcsaturrday.springlibraryproject.library.mapper.GenericMapperF;
import com.jpcsaturrday.springlibraryproject.library.model.UserF;
import com.jpcsaturrday.springlibraryproject.library.repository.GenericRepositoryF;
import org.springframework.stereotype.Service;

@Service
public class UserFService
        extends GenericServiceF<UserF, UserFDTO> {
    public UserFService(GenericRepositoryF<UserF> repository,
                        GenericMapperF<UserF, UserFDTO> mapper) {
        super(repository, mapper);
    }

    @Override
    public UserFDTO create(UserFDTO newObject) {
        RoleFDTO roleDTO = new RoleFDTO();
        roleDTO.setId(1L);
        newObject.setRole(roleDTO);
        return mapper.toDTO(repository.save(mapper.toEntity(newObject)));
    }
}
