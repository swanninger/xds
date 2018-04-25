package com.xds.services;

import com.xds.domain.Order;

public interface OrderService {

    void addOrder(Order order);

    void recallLastOrder();

    void bumpOrder(int pane);

    Boolean saveOrder();

    Integer getCurrentPage();
}
