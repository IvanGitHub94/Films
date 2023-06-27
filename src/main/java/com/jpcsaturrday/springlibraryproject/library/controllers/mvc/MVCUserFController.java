package com.jpcsaturrday.springlibraryproject.library.controllers.mvc;


import com.jpcsaturrday.springlibraryproject.library.dto.UserFDTO;
import com.jpcsaturrday.springlibraryproject.library.service.UserFService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.jpcsaturrday.springlibraryproject.library.constants.UserFRolesConstants.ADMIN;


@Controller
@Slf4j
@RequestMapping("/users")
public class MVCUserFController {

    private final UserFService userService;

    public MVCUserFController(UserFService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserFDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserFDTO userDTO,
                               BindingResult bindingResult) {
        if (userDTO.getLogin().equalsIgnoreCase(ADMIN) || userService.getUserByLogin(userDTO.getLogin()) != null) {
            bindingResult.rejectValue("login", "error.login", "Такой логин уже существует");
            return "registration";
        }
        if (userService.getUserByEmail(userDTO.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.email", "Такой e-mail уже существует");
            return "registration";
        }
        userService.create(userDTO);
        return "redirect:login";
    }

//    @GetMapping("/profile")
//    public String profile() {
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication auth = context.getAuthentication();
//        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
//        userDetails.getUserId();
//    }
}

