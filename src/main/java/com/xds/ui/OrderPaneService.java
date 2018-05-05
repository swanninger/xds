package com.xds.ui;

import com.xds.uiComponents.OrderPane;

import java.awt.*;
import java.util.List;

/**
 * Created by PhazedOut on 3/17/2018.
 */
public interface OrderPaneService {

    List<OrderPane> getPanes();
    Dimension getSize(Dimension dimension);
    OrderPane getPane(int i);
}
