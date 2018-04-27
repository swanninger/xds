package com.xds.domain;

import lombok.Getter;
import lombok.Setter;

import javax.swing.text.Document;

@Getter
@Setter
public class OrderDocument {
    private Document document;
    private Order order;

    public OrderDocument(Document document, Order order) {
        this.document = document;
        this.order = order;
    }
}
