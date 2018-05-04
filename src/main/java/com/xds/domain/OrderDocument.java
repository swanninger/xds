package com.xds.domain;

import lombok.Getter;
import javax.swing.text.Document;
import java.util.List;

/*
 This class holds a reference to an order and its documents
 1 document =  1 textPane
 */

@Getter
public class OrderDocument {
    private List<Document> documents;
    private Order order;

    public OrderDocument(List<Document> documents, Order order) {
        this.documents = documents;
        this.order = order;
    }
}
