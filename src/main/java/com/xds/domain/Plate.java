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
@EqualsAndHashCode(exclude = {"order_id"})
@Table(name = "plates")
public class Plate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plate")
    private List<Mod> mods = new LinkedList<>();

    private Integer qty;

    public Plate addMod(String name, Integer qty){
        Mod mod = new Mod(name, qty);
        mod.setPlate(this);
        this.mods.add(mod);
        return this;
    }

    public Plate addMod(Mod mod){
        mod.setPlate(this);
        this.mods.add(mod);
        return this;
    }
}