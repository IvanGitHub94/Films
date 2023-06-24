package com.jpcsaturrday.springlibraryproject.library.controllers.mvc;

import com.jpcsaturrday.springlibraryproject.library.dto.DirectorDTO;
import com.jpcsaturrday.springlibraryproject.library.service.DirectorService;
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
@RequestMapping("/directors")
@RequiredArgsConstructor
public class MVCDirectorsController {
    private final DirectorService directorService;

    /*public MVCDirectorsController(DirectorService directorService) {
        this.directorService = directorService;
    }*/

    @GetMapping
    public String getAll(Model model) {
        List<DirectorDTO> directors = directorService.listAll();
        model.addAttribute("directors", directors);
        return "directors/viewAllDirectors";
    }

    @GetMapping("/add")
    public String create() {
        return "directors/addDirector";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("directorForm") DirectorDTO newDirector) {
        log.info(newDirector.toString());
        directorService.create(newDirector);
        return "redirect:/directors";
    }
}
