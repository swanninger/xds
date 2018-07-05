//package com.xds.bootstrap;
//
//import com.xds.domain.Order;
//import com.xds.domain.Plate;
//import com.xds.services.OrderService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.security.oauth2.client.OAuth2RestOperations;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//@Slf4j
//@Component
//public class Bootstrap implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {
//    public final OrderService orderService;
//    private OAuth2RestOperations oAuth2RestTemplate;
//    private ApplicationContext context;
//
//    public Bootstrap(OrderService orderService, OAuth2RestOperations oAuth2RestTemplate) {
//        this.orderService = orderService;
//        this.oAuth2RestTemplate = oAuth2RestTemplate;
//    }
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
////        createOrders();
////        oauthLogin();
//
//    }
//
//
//    private void oauthLogin() {
//        try {
//            oAuth2RestTemplate.getAccessToken();
//            log.info(oAuth2RestTemplate.getForObject("http://localhost:8080/", String.class));
//        } catch (Exception e) {
//            System.exit(0);
//        }
//
//    }
//    private void createOrders(){
//        Order order = new Order();
//        order.setOrderNumber(1);
//        order.setOrderTime(LocalDateTime.now());
//        order.setOrderMode("ToGo");
//
//        Plate p = new Plate();
//        p.setName("BBQ");
//        p.addMod("fries",1);
//        p.addMod("coke",1);
//        order.addPlate(p);
//        order.setNameOnOrder("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//
//        orderService.addOrder(order);
//
//        for (int i = 0; i < 10; i++) {
//            order = new Order();
//            order.setOrderNumber(i+1);
//            order.setOrderTime(LocalDateTime.now());
//            order.setOrderMode("Dine-In");
//
//            p = new Plate();
//            p.setName("BBQ");
//            p.addMod("fries",1);
//            p.addMod("coke",1);
//            order.addPlate(p);
//            p = new Plate();
//            p.setName("BBQ");
//            p.addMod("fries",1);
//            p.addMod("coke",1);
//            order.addPlate(p);
//            p = new Plate();
//            p.setName("BBQ");
//            p.addMod("fries",1);
//            p.addMod("coke",1);
//            order.addPlate(p);
//            p = new Plate();
//            p.setName("BBQ");
//            p.addMod("fries",1);
//            p.addMod("coke",1);
//            order.addPlate(p);
//            p = new Plate();
//            p.setName("BBQ");
//            p.addMod("fries",1);
//            p.addMod("coke",1);
//            order.addPlate(p);
//            p = new Plate();
//            p.setName("BBQ");
//            p.addMod("fries",1);
//            p.addMod("coke",1);
//            order.addPlate(p);
//            p = new Plate();
//            p.setName("BBQ");
//            p.addMod("fries",1);
//            p.addMod("coke",1);
//            order.addPlate(p);
//            orderService.addOrder(order);
//        }
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.context = applicationContext;
//    }
//}