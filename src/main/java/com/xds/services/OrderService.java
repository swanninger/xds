package com.xds.services;

import com.xds.domain.Order;

public interface OrderService {

    Order recallLastOrder();

    Boolean bumpOrder();


}
