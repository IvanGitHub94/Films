package com.jpcsaturrday.springlibraryproject.library.repository;

import com.jpcsaturrday.springlibraryproject.library.model.Director;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorRepository extends GenericRepositoryF<Director> {


    public List<Director> findByPosition(String position);

    public List<Director> findAll();
}
