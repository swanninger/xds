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
@EqualsAndHashCode(exclude = {"order"})
@Table(name = "plates")
public class Plate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;

    @ManyToOne
    private Order order;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plate")
    private List<Mod> mods = new LinkedList<>();

    private Integer qty;

    public Plate addMod(String name, Integer qty) {
        Mod mod = new Mod(name, qty);
        mod.setPlate(this);
        this.mods.add(mod);
        return this;
    }

    public Plate addMod(Mod mod) {
        mod.setPlate(this);
        this.mods.add(mod);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.qty != null && this.qty > 1) {
            sb.append(this.qty + " ");
        }
        sb.append(this.name);
        return sb.toString();
    }
}