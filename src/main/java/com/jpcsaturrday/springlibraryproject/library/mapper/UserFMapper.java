package com.jpcsaturrday.springlibraryproject.library.mapper;

import com.jpcsaturrday.springlibraryproject.library.dto.UserFDTO;
import com.jpcsaturrday.springlibraryproject.library.model.GenericModelF;
import com.jpcsaturrday.springlibraryproject.library.model.UserF;
import com.jpcsaturrday.springlibraryproject.library.repository.OrdersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserFMapper
        extends GenericMapperF<UserF, UserFDTO> {

    private final OrdersRepository ordersRepository;

    protected UserFMapper(ModelMapper modelMapper,
                         OrdersRepository ordersRepository) {
        super(UserF.class, UserFDTO.class, modelMapper);
        this.ordersRepository = ordersRepository;
    }

    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(UserF.class, UserFDTO.class)
                .addMappings(m -> m.skip(UserFDTO::setOrders)).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(UserFDTO.class, UserF.class)
                .addMappings(m -> m.skip(UserF::setOrders)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(UserFDTO source, UserF destination) {
        if (!Objects.isNull(source.getOrders())) {
            destination.setOrders(ordersRepository.findAllById(source.getOrders()));
        }
        else {
            destination.setOrders(Collections.emptyList());
        }
    }

    @Override
    protected void mapSpecificFields(UserF source, UserFDTO destination) {
        destination.setOrders(getIds(source));
    }

    @Override
    protected List<Long> getIds(UserF entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getOrders())
                ? null
                : entity.getOrders().stream()
                .map(GenericModelF::getId)
                .collect(Collectors.toList());
    }
}
