package com.xds.domain;

import lombok.Data;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by PhazedOut on 3/29/2018.
 */
@Data
public class Plate {

    private final String name;

    private CopyOnWriteArrayList<String> categories;

    private CopyOnWriteArrayList<String> sides;
}
