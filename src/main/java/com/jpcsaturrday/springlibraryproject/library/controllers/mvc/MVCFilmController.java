package com.jpcsaturrday.springlibraryproject.library.controllers.mvc;

import com.jpcsaturrday.springlibraryproject.library.dto.FilmDTO;
import com.jpcsaturrday.springlibraryproject.library.service.DirectorService;
import com.jpcsaturrday.springlibraryproject.library.service.FilmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/films")
public class MVCFilmController {
    private final FilmService filmService;
    private final DirectorService directorService;


    @GetMapping
    public String getAll(Model model) {
        List<FilmDTO> films = filmService.listAll();
        model.addAttribute("films", films);
        model.addAttribute("directors", directorService.findAll());
        return "films/viewAllFilms";
    }

    @GetMapping("/add")
    public String create() {
        return "films/addFilm";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("filmForm") FilmDTO newFilm) {
        log.info(newFilm.toString());
        filmService.create(newFilm);
        return "redirect:/films";
    }
}

