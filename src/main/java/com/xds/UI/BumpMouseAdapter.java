package com.xds.UI;

import com.xds.services.OrderService;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BumpMouseAdapter extends MouseAdapter {
    private final OrderService orderService;
    private int textPane;

    BumpMouseAdapter(OrderService orderService, int i){
        this.orderService = orderService;
        this.textPane = i;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Touch " + textPane);
        orderService.bumpOrder(textPane);
    }
}