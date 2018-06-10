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
        createOrders();

    }

    private void createOrders(){
        Order order = new Order();
        order.setOrderNumber(1);
        order.setOrderTime(LocalDateTime.now());
        order.setOrderMode("ToGo");

        Plate p = new Plate();
        p.setName("BBQ");
        p.addMod("fries",1);
        p.addMod("coke",1);
        order.addPlate(p);
        order.setNameOnOrder("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        orderService.addOrder(order);

        for (int i = 0; i < 10; i++) {
            order = new Order();
            order.setOrderNumber(i+1);
            order.setOrderTime(LocalDateTime.now());
            order.setOrderMode("Dine-In");

            p = new Plate();
            p.setName("BBQ");
            p.addMod("fries",1);
            p.addMod("coke",1);
            order.addPlate(p);
            p = new Plate();
            p.setName("BBQ");
            p.addMod("fries",1);
            p.addMod("coke",1);
            order.addPlate(p);
            p = new Plate();
            p.setName("BBQ");
            p.addMod("fries",1);
            p.addMod("coke",1);
            order.addPlate(p);
            p = new Plate();
            p.setName("BBQ");
            p.addMod("fries",1);
            p.addMod("coke",1);
            order.addPlate(p);
            p = new Plate();
            p.setName("BBQ");
            p.addMod("fries",1);
            p.addMod("coke",1);
            order.addPlate(p);
            p = new Plate();
            p.setName("BBQ");
            p.addMod("fries",1);
            p.addMod("coke",1);
            order.addPlate(p);
            p = new Plate();
            p.setName("BBQ");
            p.addMod("fries",1);
            p.addMod("coke",1);
            order.addPlate(p);
            orderService.addOrder(order);
        }
    }
}