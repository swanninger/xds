package com.xds.api.v1.model;

import com.xds.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PlateDTO {

    private String name;
    private String category;
    private Integer qty;
    private List<ModDTO> mods;
    private Order order;
}
