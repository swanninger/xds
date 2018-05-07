package com.xds.domain;

import com.xds.ui.extensions.OrderDocument;
import lombok.Data;

import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by PhazedOut on 3/17/2018.
 */

@Data
public class Order {

    private final Integer orderNumber;
    private final LocalDateTime orderTime;
    private final String orderType;
    private LocalDateTime bumpTime;

    private List<Plate> plates = new LinkedList<>();

    @Transient
    private List<OrderDocument> documents;


    public Order(int orderNumber, LocalDateTime orderTime, String orderType) {
        this.orderNumber = orderNumber;
        this.orderTime = orderTime;
        this.orderType = orderType;
    }

    public Order addPlate(Plate p){
        plates.add(p);
        return this;
    }
}
