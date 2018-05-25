package com.xds.api.v1.mapper;

import com.xds.api.v1.model.OrderDTO;
import com.xds.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order orderDtoToOrder(OrderDTO orderDTO);
}
