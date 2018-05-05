package com.xds.domain;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by PhazedOut on 3/29/2018.
 */
@Data
public class Plate {

    private final String name;
    private List<String> categories = new LinkedList<>();
    private List<String> sides = new LinkedList<>();

    public Plate(String name) {
        this.name = name;

    }

    public Plate addSide(String s){
        this.sides.add(s);
        return this;
    }

}
