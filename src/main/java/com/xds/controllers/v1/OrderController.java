package com.xds.controllers.v1;

import com.xds.api.v1.mapper.OrderMapper;
import com.xds.api.v1.model.OrderDTO;
import com.xds.domain.Order;
import com.xds.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    public final OrderMapper orderMapper;

    private final OrderService orderService;

    public OrderController(OrderMapper orderMapper, OrderService orderService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = orderService.addOrder(orderMapper.orderDtoToOrder(orderDTO));

        return orderMapper.orderToOrderDto(order);
    }
}
