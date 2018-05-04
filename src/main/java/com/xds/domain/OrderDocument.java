package com.xds.domain;

import lombok.Getter;
import javax.swing.text.Document;
import java.util.List;

/*
 This class holds a reference to an order and its documents
 */

@Getter
public class OrderDocument {
    private List<Document> documents;
    private Order order;

    public OrderDocument(Order order) {
        this.order = order;
    }

    void addDocument(Document d){
        documents.add(d);
    }
}
