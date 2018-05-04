package com.xds.UI;

import com.xds.domain.Order;
import com.xds.domain.OrderDocument;
import com.xds.domain.Plate;
import com.xds.services.KdsStyles;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Iterator;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final KdsStyles kdsStyles;
    private final TextPaneService textPaneService;

    private Dimension dimension;

    public DocumentServiceImpl(KdsStyles kdsStyles, TextPaneService textPaneService) {
        this.kdsStyles = kdsStyles;
        this.textPaneService = textPaneService;
    }

    @Override
    public OrderDocument createOrderDocument(Order order) {
        this.dimension = textPaneService.getSize(dimension);

        OrderDocument orderDocument = new OrderDocument(order);
        Iterator<Plate> plates = order.getPlates().iterator();



        while (plates.hasNext()){




        }
        return null;
    }

    void addLine(){

    }
}
