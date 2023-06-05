package com.jpcsaturrday.springlibraryproject.library.service;

import com.jpcsaturrday.springlibraryproject.library.dto.OrdersDTO;
import com.jpcsaturrday.springlibraryproject.library.mapper.OrdersMapper;
import com.jpcsaturrday.springlibraryproject.library.model.Orders;
import com.jpcsaturrday.springlibraryproject.library.repository.OrdersRepository;
import org.springframework.stereotype.Service;

@Service
public class OrdersService
        extends GenericServiceF<Orders, OrdersDTO> {
    protected OrdersService(OrdersRepository ordersRepository,
                            OrdersMapper ordersMapper) {
        super(ordersRepository, ordersMapper);
    }
}
