package com.xds.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(exclude = {"plate"})
@Table(name = "mods")
public class Mod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer qty;

    @ManyToOne
    private Plate plate;

    public Mod() {
    }

    public Mod(String name, Integer qty) {
        this.name = name;
        this.qty = qty;
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
