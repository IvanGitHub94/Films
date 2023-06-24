package com.jpcsaturrday.springlibraryproject.library.mapper;

import com.jpcsaturrday.springlibraryproject.library.dto.FilmDTO;
import com.jpcsaturrday.springlibraryproject.library.model.Film;
import com.jpcsaturrday.springlibraryproject.library.model.GenericModelF;
import com.jpcsaturrday.springlibraryproject.library.repository.DirectorRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class FilmMapper
        extends GenericMapperF<Film, FilmDTO> {
    private final DirectorRepository directorRepository;

    protected FilmMapper(ModelMapper mapper, DirectorRepository directorRepository) {
        super(Film.class, FilmDTO.class, mapper);
        this.directorRepository = directorRepository;
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
}
