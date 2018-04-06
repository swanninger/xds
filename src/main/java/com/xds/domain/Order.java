package com.xds.domain;

import java.time.LocalDateTime;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by PhazedOut on 3/17/2018.
 */

public class Order {

    private final int orderNumber;

    private final LocalDateTime orderTime;

    private CopyOnWriteArrayList<Plate> plates;

    private boolean bumped = false;

    public Order(int orderNumber, LocalDateTime orderTime) {
        this.orderNumber = orderNumber;
        this.orderTime = orderTime;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public CopyOnWriteArrayList<Plate> getPlates() {
        return plates;
    }

    public void addPlate(Plate plate) {
        this.plates.add(plate);
    }

    public void bump(){
        bumped = true;
    }

    public boolean isBumped() {
        return bumped;
    }
}
