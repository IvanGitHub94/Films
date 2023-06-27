package com.jpcsaturrday.springlibraryproject.library.service;

import com.jpcsaturrday.springlibraryproject.library.dto.RoleFDTO;
import com.jpcsaturrday.springlibraryproject.library.dto.UserFDTO;
import com.jpcsaturrday.springlibraryproject.library.mapper.GenericMapperF;
import com.jpcsaturrday.springlibraryproject.library.model.UserF;
import com.jpcsaturrday.springlibraryproject.library.repository.GenericRepositoryF;
import com.jpcsaturrday.springlibraryproject.library.repository.UserFRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserFService
        extends GenericServiceF<UserF, UserFDTO> {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserFService(GenericRepositoryF<UserF> repository,
                       GenericMapperF<UserF, UserFDTO> mapper,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(repository, mapper);
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserFDTO create(UserFDTO newObject) {
        RoleFDTO roleDTO = new RoleFDTO();
        roleDTO.setId(1L);
        newObject.setRole(roleDTO);
        newObject.setPassword(bCryptPasswordEncoder.encode(newObject.getPassword()));
        newObject.setCreatedBy("REGISTRATION FORM");
        newObject.setCreatedWhen(LocalDateTime.now());
        return mapper.toDTO(repository.save(mapper.toEntity(newObject)));
    }

    public UserF getUserByLogin(String login) {
        if (repository instanceof UserFRepository) {
            Optional<UserF> userFOpt = ((UserFRepository) repository).findByLogin(login);
            return userFOpt
                    .orElse(null);
        }
        throw new IllegalArgumentException("Wrong type of repository: " + repository.getClass().getName());
    }

    public UserF getUserByEmail(String email) {
        if (repository instanceof UserFRepository) {
            Optional<UserF> userFOpt = ((UserFRepository) repository).findByEmail(email);
            return userFOpt
                    .orElse(null);
        }
        throw new IllegalArgumentException("Wrong type of repository: " + repository.getClass().getName());
    }
}
