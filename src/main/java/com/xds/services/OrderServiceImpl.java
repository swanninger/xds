package com.xds.services;

import com.xds.domain.Order;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
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
    public void recallLastOrder() {
        orderList.add(recallList.pop());
    }

    @Override
    public void bumpOrder(int textPane) {
        // TODO: 4/24/2018
    }

    @Override
    public Boolean saveOrder() {
        // TODO: 4/24/2018
        return null;
    }

    @Override
    public Integer getCurrentPage() {
        return currentPage;
    }
}
