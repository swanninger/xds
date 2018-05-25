package com.xds.api.v1.model;

import com.xds.domain.Plate;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDTO {

    private Integer orderNumber;
    private LocalDateTime orderTime;
    private String orderMode;
    private String nameOnOrder;
    private List<Plate> plates;


    public OrderDTO(Integer orderNumber, LocalDateTime orderTime, List<Plate> plates) {
        this.orderNumber = orderNumber;
        this.orderTime = orderTime;
        this.plates = plates;
    }
}
