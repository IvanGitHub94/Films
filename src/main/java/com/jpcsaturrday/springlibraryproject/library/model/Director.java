package com.jpcsaturrday.springlibraryproject.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "directors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "director_sequence", allocationSize = 1)
public class Director extends GenericModelF {

    @Column(name = "directors_fio", nullable = false)
    private String directorFio;

    @Column(name = "position")
    private String position;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "films_directors",
            joinColumns = @JoinColumn(name = "director_id"), foreignKey = @ForeignKey(name = "FK_DIRECTORS_FILMS"),
            inverseJoinColumns = @JoinColumn(name = "film_id"), inverseForeignKey = @ForeignKey(name = "FK_FILMS_DIRECTORS"))
    private List<Film> films;
}
