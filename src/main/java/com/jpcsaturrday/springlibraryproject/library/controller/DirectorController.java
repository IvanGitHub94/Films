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
@RequestMapping("/directors")  // http://localhost:8081/swagger-ui/index.html#/directors
@Tag(name = "Режиссеры", description = "Контроллер для работы с режиссерами фильмов")
public class DirectorController
        extends GenericControllerF<Director> {
    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository;

    public DirectorController(GenericRepositoryF<Director> genericRepositoryF,
                              FilmRepository filmRepository,
                              DirectorRepository directorRepository) {
        super(genericRepositoryF);
        this.filmRepository = filmRepository;
        this.directorRepository = directorRepository;
    }

    @Operation(description = "Добавить фильм к режиссеру")
    @RequestMapping(value = "/addFilm",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Director> addFilm(@RequestParam(value = "filmId") Long filmId,
                                            @RequestParam(value = "directorId") Long directorId) {
        Film film = filmRepository.findById(filmId).orElseThrow(
                () -> new NotFoundException("фильм не найден")
        );
        Director director = directorRepository.findById(directorId).orElseThrow(
                () -> new NotFoundException("режиссер не найден")
        );
        director.getFilms().add(film);

        return ResponseEntity.status(HttpStatus.OK).body(directorRepository.save(director));
    }
}
