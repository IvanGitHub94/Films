package com.jpcsaturrday.springlibraryproject.library.repository;

import com.jpcsaturrday.springlibraryproject.library.model.Film;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends GenericRepositoryF<Film> {

    public List<Film> findByFilmTitle(String filmTitle);

}
