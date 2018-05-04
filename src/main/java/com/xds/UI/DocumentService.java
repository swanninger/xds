package com.xds.UI;

import com.xds.domain.Order;
import com.xds.domain.OrderDocument;

public interface DocumentService {
    OrderDocument createOrderDocument(Order order);
}
