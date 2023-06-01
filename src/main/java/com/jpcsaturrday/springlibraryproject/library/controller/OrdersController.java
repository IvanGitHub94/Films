package com.jpcsaturrday.springlibraryproject.library.controller;

import com.jpcsaturrday.springlibraryproject.library.model.Orders;
import com.jpcsaturrday.springlibraryproject.library.repository.OrdersRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders/info") // http://localhost:8081/swagger-ui/index.html#/orders/info
@Tag(name = "Прокат фильмов",
        description = "Контроллер для работы с прокатом фильмов пользователям сервиса")
public class OrdersController
        extends GenericControllerF<Orders> {

    public OrdersController(OrdersRepository repository) {
        super(repository);
    }
}
