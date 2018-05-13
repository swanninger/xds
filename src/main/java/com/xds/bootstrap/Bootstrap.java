package com.xds.bootstrap;

import com.xds.domain.Order;
import com.xds.domain.Plate;
import com.xds.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Slf4j
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
    public final OrderService orderService;

    public Bootstrap(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Order order = new Order(1, LocalDateTime.now(), "ToGo");

        Plate p = new Plate("BBQ");
        p.addSide("fries");
        p.addSide("coke");
        order.addPlate(p);

        orderService.addOrder(order);

        for (int i = 0; i < 10; i++) {
            order = new Order(1, LocalDateTime.now(), "Dine-In");

            p = new Plate("BBQ");
            p.addSide("fries");
            p.addSide("coke");
            order.addPlate(p);

            p = new Plate("BBQ");
            p.addSide("fries");
            p.addSide("coke");
            order.addPlate(p);

            p = new Plate("BBQ");
            p.addSide("fries");
            p.addSide("coke");
            order.addPlate(p);

            p = new Plate("BBQ");
            p.addSide("fries");
            p.addSide("coke");
            order.addPlate(p);

            p = new Plate("BBQ");
            p.addSide("fries");
            p.addSide("coke");
            order.addPlate(p);

            p = new Plate("BBQ");
            p.addSide("fries");
            p.addSide("coke");
            order.addPlate(p);

            p = new Plate("BBQ");
            p.addSide("fries");
            p.addSide("coke");
            order.addPlate(p);

            orderService.addOrder(order);
        }


    }
}