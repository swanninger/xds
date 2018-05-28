package com.xds.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Integer id;

    private Integer orderNumber;
    private LocalDateTime orderTime;
    private String orderMode;
    private String nameOnOrder;
    private List<PlateDTO> plates;
}
