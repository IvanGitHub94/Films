package com.jpcsaturrday.springlibraryproject.library.repository;

import com.jpcsaturrday.springlibraryproject.library.model.UserF;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserFRepository
        extends GenericRepositoryF<UserF> {
    public Optional<UserF> findByLogin(String login);

    Optional<UserF> findByEmail(String email);
}
