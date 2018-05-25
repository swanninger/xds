package com.xds.domain;

import com.xds.ui.extensions.OrderDocument;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by PhazedOut on 3/17/2018.
 */

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer orderNumber;
    private LocalDateTime orderTime;
    private String orderMode;
    private LocalDateTime bumpTime;
    private String nameOnOrder;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<Plate> plates = new LinkedList<>();

    @Transient
    private List<OrderDocument> documents;

    public Order addPlate(Plate plate){
        plate.setOrder(this);
        plates.add(plate);
        return this;
    }
}
