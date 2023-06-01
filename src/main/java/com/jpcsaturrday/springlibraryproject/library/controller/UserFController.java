package com.jpcsaturrday.springlibraryproject.library.controller;

import com.jpcsaturrday.springlibraryproject.library.model.UserF;
import com.jpcsaturrday.springlibraryproject.library.repository.UserFRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users") // http://localhost:8081/swagger-ui/index.html#/users
@Tag(name = "Пользователи",
        description = "Контроллер для работы с пользователями сервиса проката фильмов")
public class UserFController
        extends GenericControllerF<UserF> {

    public UserFController(UserFRepository userFRepository) {
        super(userFRepository);
    }
}
