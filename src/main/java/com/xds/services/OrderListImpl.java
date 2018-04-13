package com.xds.services;

import org.springframework.stereotype.Service;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by PhazedOut on 3/29/2018.
 */
@Service
public class OrderListImpl<Order> extends CopyOnWriteArrayList<Order> implements OrderList<Order>{
}
