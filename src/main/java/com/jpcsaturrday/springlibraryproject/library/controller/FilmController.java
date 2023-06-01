package com.jpcsaturrday.springlibraryproject.library.controller;

import com.jpcsaturrday.springlibraryproject.library.model.Director;
import com.jpcsaturrday.springlibraryproject.library.model.Film;
import com.jpcsaturrday.springlibraryproject.library.repository.DirectorRepository;
import com.jpcsaturrday.springlibraryproject.library.repository.FilmRepository;
import com.jpcsaturrday.springlibraryproject.library.repository.GenericRepositoryF;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

@RestController
@RequestMapping("/films") // http://localhost:8081/swagger-ui/index.html#/films
@Tag(name = "Фильмы",
        description = "Контроллер для работы с фильмами")
public class FilmController extends GenericControllerF<Film> {
    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository;

    public FilmController(GenericRepositoryF<Film> genericRepositoryF,
                          FilmRepository filmRepository,
                          DirectorRepository directorRepository) {
        super(genericRepositoryF);
        this.filmRepository = filmRepository;
        this.directorRepository = directorRepository;
    }

    @Operation(description = "Добавить фильм к режиссеру")
    @RequestMapping(value = "/addDirector",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Film> addFilm(@RequestParam(value = "filmId") Long filmId,
                                        @RequestParam(value = "directorId") Long directorId) {
        Film film = filmRepository.findById(filmId).orElseThrow(
                () -> new NotFoundException("фильм не найден")
        );
        Director director = directorRepository.findById(directorId).orElseThrow(
                () -> new NotFoundException("режиссер не найден")
        );
        film.getDirectors().add(director);
        return ResponseEntity.status(HttpStatus.OK).body(filmRepository.save(film));
    }
}
