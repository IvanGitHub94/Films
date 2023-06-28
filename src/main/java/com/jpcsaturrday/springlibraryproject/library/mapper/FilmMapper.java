package com.jpcsaturrday.springlibraryproject.library.mapper;

import com.jpcsaturrday.springlibraryproject.library.dto.DirectorDTO;
import com.jpcsaturrday.springlibraryproject.library.dto.FilmDTO;
import com.jpcsaturrday.springlibraryproject.library.model.Director;
import com.jpcsaturrday.springlibraryproject.library.model.Film;
import com.jpcsaturrday.springlibraryproject.library.model.GenericModelF;
import com.jpcsaturrday.springlibraryproject.library.repository.DirectorRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class FilmMapper
        extends GenericMapperF<Film, FilmDTO> {
    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    protected FilmMapper(ModelMapper mapper, DirectorRepository directorRepository, DirectorMapper directorMapper) {
        super(Film.class, FilmDTO.class, mapper);
        this.directorRepository = directorRepository;
        this.directorMapper = directorMapper;
    }

    @PostConstruct
    @Override
    public void setupMapper() {
        modelMapper.createTypeMap(Film.class, FilmDTO.class)
                //.addMappings(m -> m.skip(FilmDTO::setDirectorIds)).setPostConverter(toDTOConverter());
                .addMappings(mapping -> {
                            mapping.skip(FilmDTO::setDirectorIds);
                        }
                )
                .setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(FilmDTO.class, Film.class)
                //.addMappings(m -> m.skip(Film::setDirectors)).setPostConverter(toEntityConverter());
                .addMappings(mapping -> mapping.skip(Film::setDirectors)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(FilmDTO source, Film destination) {
        if (!Objects.isNull(source.getDirectorIds())) {
            destination.setDirectors(directorRepository.findAllById(source.getDirectorIds()));
        }
        else {
            destination.setDirectors(Collections.emptyList());
        }
    }

    @Override
    protected void mapSpecificFields(Film source, FilmDTO destination) {
        destination.setDirectorIds(getIds(source));
    }

    @Override
    protected List<Long> getIds(Film film) {
        return Objects.isNull(film) || Objects.isNull(film.getDirectors())
                ? null
                : film.getDirectors().stream()
                .map(GenericModelF::getId)
                .collect(Collectors.toList());
    }


    @Override
    public FilmDTO toDTO(Film film) {
        FilmDTO dto = new FilmDTO();
        dto.setCountry(film.getCountry());
        dto.setFilmTitle(film.getFilmTitle());
        dto.setGenre(film.getGenre());
        dto.setPremiereDate(film.getPremiereDate());
        if(!film.getDirectors().isEmpty()) {
            dto.setDirectorDTO(directorMapper.toDTO(film.getDirectors().get(0)));
        }
        return dto;
    }

    @Override
    public Film toEntity(FilmDTO filmDTO) {
        Film film = new Film();
        film.setFilmTitle(filmDTO.getFilmTitle());
        film.setCountry(filmDTO.getCountry());
        film.setGenre(filmDTO.getGenre());
        film.setPremiereDate(filmDTO.getPremiereDate());
        List<Director> directors = new ArrayList<>();
        // Некрасиво, но пока работает
            if(!filmDTO.getDirectorDTO().getDirectorFio().equals("") &&
                    !filmDTO.getDirectorDTO().getPosition().equals("")) {
                directors.add(directorMapper.toEntity(filmDTO.getDirectorDTO()));
            }
        film.setDirectors(directors);
        return film;
    }

}
