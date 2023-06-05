package com.jpcsaturrday.springlibraryproject.library.mapper;

import com.jpcsaturrday.springlibraryproject.library.dto.OrdersDTO;
import com.jpcsaturrday.springlibraryproject.library.model.Orders;
import com.jpcsaturrday.springlibraryproject.library.repository.FilmRepository;
import com.jpcsaturrday.springlibraryproject.library.repository.UserFRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.List;

@Component
public class OrdersMapper
        extends GenericMapperF<Orders, OrdersDTO> {
    private final FilmRepository filmRepository;
    private final UserFRepository userRepository;

    protected OrdersMapper(ModelMapper mapper,
                                 FilmRepository filmRepository,
                                 UserFRepository userRepository) {
        super(Orders.class, OrdersDTO.class, mapper);
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void setupMapper() {
        super.modelMapper.createTypeMap(Orders.class, OrdersDTO.class)
                .addMappings(m -> m.skip(OrdersDTO::setUserFId))
                .addMappings(m -> m.skip(OrdersDTO::setFilmId))
                .setPostConverter(toDTOConverter());

        super.modelMapper.createTypeMap(OrdersDTO.class, Orders.class)
                .addMappings(m -> m.skip(Orders::setUser))
                .addMappings(m -> m.skip(Orders::setFilm))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(OrdersDTO source, Orders destination) {
        destination.setFilm(filmRepository.findById(source.getFilmId()).orElseThrow(() -> new NotFoundException("Фильма не найдено")));
        destination.setUser(userRepository.findById(source.getUserFId()).orElseThrow(() -> new NotFoundException("Пользователя не найдено")));
    }

    @Override
    protected void mapSpecificFields(Orders source, OrdersDTO destination) {
        destination.setUserFId(source.getUser().getId());
        destination.setFilmId(source.getFilm().getId());
    }

    @Override
    protected List<Long> getIds(Orders entity) {
        throw new UnsupportedOperationException("Метод недоступен");
    }
}
