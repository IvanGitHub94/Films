package com.jpcsaturrday.springlibraryproject.library.service;

import com.jpcsaturrday.springlibraryproject.library.dto.DirectorDTO;
import com.jpcsaturrday.springlibraryproject.library.dto.FilmDTO;
import com.jpcsaturrday.springlibraryproject.library.mapper.DirectorMapper;
import com.jpcsaturrday.springlibraryproject.library.mapper.FilmMapper;
import com.jpcsaturrday.springlibraryproject.library.model.Director;
import com.jpcsaturrday.springlibraryproject.library.model.Film;
import com.jpcsaturrday.springlibraryproject.library.repository.DirectorRepository;
import com.jpcsaturrday.springlibraryproject.library.repository.FilmRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class FilmService
        extends GenericServiceF<Film, FilmDTO> {
    private final DirectorRepository directorRepository;
    private final FilmRepository filmRepository;
    private final DirectorMapper directorMapper;

    protected FilmService(FilmRepository repository,
                          FilmMapper mapper,
                          DirectorRepository directorRepository,
                          FilmRepository filmRepository,
                          DirectorMapper directorMapper) {
        super(repository, mapper);
        this.directorRepository = directorRepository;
        this.filmRepository = filmRepository;
        this.directorMapper = directorMapper;
    }

    public FilmDTO addDirector(final Long filmId,
                             final Long directorId) {
        FilmDTO film = getOne(filmId);
        Director director = directorRepository.findById(directorId).orElseThrow(() -> new NotFoundException("Режиссер не найден"));
        film.getDirectorIds().add(director.getId());
        update(film);
        return film;
    }

    public Optional<Film> findByFilmTitle(String filmTitle) {
        List<Film> existingFilms = filmRepository.findByFilmTitle(filmTitle);
        if (existingFilms.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(existingFilms.get(0));
    }

    public void updateDirector(Film film, Director director) {
        List<Director> directors = new ArrayList<>();
        directors.add(director);
        film.setDirectors(directors);
        filmRepository.save(film);
    }
}
