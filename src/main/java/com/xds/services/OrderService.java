package com.xds.services;

import com.xds.domain.Order;

public interface OrderService {

    Order addOrder(Order order);

    void recallOrder();

    void bumpOrder(int pane);

    Order saveOrder(Order order);

    void updateOrders();

    void pageRight();

    void pageLeft();

    void pageHome();

    void pageLast();
}
