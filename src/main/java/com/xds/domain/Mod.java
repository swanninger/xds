package com.xds.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "mods")
public class Mod {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer qty;

    @ManyToOne
    @JoinColumn(name = "plate_id")
    private Plate plate_id;


    public Mod(String name, Integer qty) {
        this.name = name;
        this.qty = qty;
    }

    @Override
    public String toString(){
         StringBuilder sb = new StringBuilder();
         if (this.qty > 1){
             sb.append(this.qty);
         }
         sb.append(this.name);
        return sb.toString();
    }
}
