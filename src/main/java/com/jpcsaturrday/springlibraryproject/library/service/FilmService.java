package com.jpcsaturrday.springlibraryproject.library.service;

import com.jpcsaturrday.springlibraryproject.library.dto.FilmDTO;
import com.jpcsaturrday.springlibraryproject.library.mapper.FilmMapper;
import com.jpcsaturrday.springlibraryproject.library.model.Director;
import com.jpcsaturrday.springlibraryproject.library.model.Film;
import com.jpcsaturrday.springlibraryproject.library.repository.DirectorRepository;
import com.jpcsaturrday.springlibraryproject.library.repository.FilmRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class FilmService
        extends GenericServiceF<Film, FilmDTO> {
    private final DirectorRepository directorRepository;

    protected FilmService(FilmRepository repository,
                          FilmMapper mapper,
                          DirectorRepository directorRepository) {
        super(repository, mapper);
        this.directorRepository = directorRepository;
    }

    public FilmDTO addDirector(final Long filmId,
                             final Long directorId) {
        FilmDTO film = getOne(filmId);
        Director director = directorRepository.findById(directorId).orElseThrow(() -> new NotFoundException("Режиссер не найден"));
        film.getDirectorIds().add(director.getId());
        update(film);
        return film;
    }
}
