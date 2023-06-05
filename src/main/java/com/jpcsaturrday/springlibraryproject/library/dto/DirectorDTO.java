package com.jpcsaturrday.springlibraryproject.library.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
}
