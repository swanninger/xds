package com.xds.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlateDTO {

    private String name;
    private String category;
    private Integer qty;
    private List<ModDTO> mods;
}
