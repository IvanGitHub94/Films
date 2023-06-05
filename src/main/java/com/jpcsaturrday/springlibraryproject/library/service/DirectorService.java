package com.jpcsaturrday.springlibraryproject.library.service;

import com.jpcsaturrday.springlibraryproject.library.dto.DirectorDTO;
import com.jpcsaturrday.springlibraryproject.library.mapper.DirectorMapper;
import com.jpcsaturrday.springlibraryproject.library.model.Director;
import com.jpcsaturrday.springlibraryproject.library.model.Film;
import com.jpcsaturrday.springlibraryproject.library.repository.DirectorRepository;
import com.jpcsaturrday.springlibraryproject.library.repository.FilmRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class DirectorService
        extends GenericServiceF<Director, DirectorDTO> {

    private final FilmRepository filmRepository;


    public DirectorService(DirectorRepository repository,
                           DirectorMapper directorMapper,
                           FilmRepository filmRepository) {
        super(repository, directorMapper);
        this.filmRepository = filmRepository;
    }

    public DirectorDTO addFilm(Long filmId,
                             Long directorId) {
        Film film = filmRepository.findById(filmId).orElseThrow(() -> new NotFoundException("Фильм не найден!"));
        DirectorDTO director = getOne(directorId);
        director.getFilmIds().add(film.getId());
        update(director);
        return director;
    }
}
