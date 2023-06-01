package com.jpcsaturrday.springlibraryproject.library.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "films")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SequenceGenerator(name = "default_generator", sequenceName = "film_sequence", allocationSize = 1)
public class Film extends GenericModelF {

    @Column(name = "title", nullable = false)
    private String filmTitle;

    @Column(name = "genre", nullable = false)
    @Enumerated
    private GenreF genre;

    @Column(name = "premiere_year", nullable = false)
    private LocalDate premiereDate;

    @Column(name = "country")
    private String country;

    @ManyToMany
    @JoinTable(name = "films_directors",
            joinColumns = @JoinColumn(name = "film_id"), foreignKey = @ForeignKey(name = "FK_FILMS_DIRECTORS"),
            inverseJoinColumns = @JoinColumn(name = "director_id"), inverseForeignKey = @ForeignKey(name = "FK_DIRECTORS_FILMS"))
    private List<Director> directors;

}
