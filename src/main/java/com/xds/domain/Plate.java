package com.xds.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.*;

/**
 * Created by PhazedOut on 3/29/2018.
 */
@Data
@Entity
@Table(name = "plates")
public class Plate {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String category;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToMany(mappedBy = "plate_id")
    private List<Mod> mods = new LinkedList<>();

    private Integer qty;

    public Plate(String name) {
        this.name = name;
    }

    public Plate addMod(String name, Integer qty){
        this.mods.add(new Mod(name, qty));
        return this;
    }
}