package com.xds.api.v1.mapper;

import com.xds.api.v1.model.OrderDTO;
import com.xds.domain.Order;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {

    Order orderDtoToOrder(OrderDTO orderDTO);

    OrderDTO orderToOrderDto(Order order);
}
