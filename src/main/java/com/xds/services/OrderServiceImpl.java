package com.xds.services;

import com.xds.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private Collection<Order> orderList;
    private Deque<Order> recallList;

    private Integer currentPage;
    private Integer numDocuments;

    public OrderServiceImpl() {
        this.recallList = new LinkedList<>();
        this.orderList = new CopyOnWriteArrayList<>();

        currentPage = 0;
        numDocuments = 0;
    }

    @Override
    public void addOrder(Order order) {
        orderList.add(order);
    }

    @Override
    public void recallOrder() {
        log.info("recall order");
//        orderList.add(recallList.pop());
    }

    @Override
    public void bumpOrder(int textPane) {
        // TODO: 4/24/2018
        log.info("Bump " + textPane);
    }

    @Override
    public Boolean saveOrder() {
        // TODO: 4/24/2018
        return null;
    }

    @Override
    public void pageRight() {
        log.info("Page Right");
//                System.out.println(orderList.size() / paneList.size());
//                if (numDocuments / paneList.size() > currentPage) {
//                    currentPage++;
//                    page.setText("Page " + (currentPage + 1) + " ");
//                    updateOrders();
//                } else {
//                    System.out.println("No more pages");
//                }
//                currentPage++;
//                page.setText("Page " + (currentPage + 1) + " ");
    }

    @Override
    public void pageLeft() {
        log.info("Page Left");
//                if (currentPage > 0) {
//                    currentPage--;
//                    page.setText("Page " + (currentPage + 1) + " ");
//                    updateOrders();
//                } else {
//                    System.out.println("At first page");
//                }
    }

    @Override
    public void pageHome() {
        log.info("Home");
//                if (currentPage != 0) {
//                    currentPage = 0;
//                    page.setText("Page 1");
//                    updateOrders();
//                } else {
//                    System.out.println("Already Home");
//                }
    }
}
