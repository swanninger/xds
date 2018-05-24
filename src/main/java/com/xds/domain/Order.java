package com.xds.domain;

import com.xds.ui.extensions.OrderDocument;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by PhazedOut on 3/17/2018.
 */

@Data
@Entity
@EqualsAndHashCode(exclude = "plates")
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer orderNumber;

    private LocalDateTime orderTime;

    private String orderMode;

    private LocalDateTime bumpTime;

    @OneToMany(mappedBy = "order")
    private List<Plate> plates = new LinkedList<>();

    @Transient
    private List<OrderDocument> documents;


    public Order(int orderNumber, LocalDateTime orderTime, String orderMode) {
        this.orderNumber = orderNumber;
        this.orderTime = orderTime;
        this.orderMode = orderMode;
    }

    public Order(int orderNumber, LocalDateTime orderTime) {
        this.orderNumber = orderNumber;
        this.orderTime = orderTime;
    }

    public Order addPlate(Plate p){
        plates.add(p);
        return this;
    }
}
