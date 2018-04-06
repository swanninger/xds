package com.xds.UI;

import com.xds.domain.Order;

import javax.swing.text.Document;

/**
 * Created by Scott on 5/15/2017.
 *
 * This class is used to display the order information. It holds a reference to the Document to be
 * displayed and the Order object used to create it.
 *
 */

public class KdsDocument {
    private Document document;
    private Order order;

    KdsDocument(Document d, Order o){
        this.document = d;
        this.order = o;
    }

    public Document getDocument() {
        return document;
    }

    public Order getOrder() {
        return order;
    }
}
