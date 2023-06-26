package com.jpcsaturrday.springlibraryproject.library.dto;

import com.jpcsaturrday.springlibraryproject.library.model.Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class FilmDTO extends GenericFDTO{
    private String filmTitle;
    private Genre genre;
    private LocalDate premiereDate;
    private String country;
    private List<Long> directorIds;
    private DirectorDTO directorDTO = new DirectorDTO("", "");
}
