package com.jpcsaturrday.springlibraryproject.library.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class DirectorDTO extends GenericFDTO{
    private String directorFio;
    //private LocalDate birthDate;
    private String position;
    private List<Long> filmIds;

    public DirectorDTO (String directorFio, String position) {
        this.directorFio = directorFio;
        this.position = position;
        this.filmIds = new ArrayList<>();
    }
}
