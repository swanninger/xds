package com.xds.domain;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by PhazedOut on 3/17/2018.
 */

@Data
public class Order {

    private final Integer orderNumber;
    private final LocalDateTime orderTime;
    private String orderType;
    private LocalDateTime bumpTime;

    private CopyOnWriteArrayList<Plate> plates;


    public Order(int orderNumber, LocalDateTime orderTime) {
        this.orderNumber = orderNumber;
        this.orderTime = orderTime;
    }
}
