package com.xds.ui;

import com.xds.domain.Order;
import com.xds.uiComponents.OrderDocument;
import com.xds.domain.Plate;
import com.xds.services.KdsStyles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.util.LinkedList;

/**
 * 5/4/2018 piKDS
 */

@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {
    private final KdsStyles kdsStyles;
    private final OrderPaneService orderPaneService;

    private Dimension dimension;
    private JTextPane dummyPane;

    public DocumentServiceImpl(KdsStyles kdsStyles, OrderPaneService orderPaneService) {
        this.kdsStyles = kdsStyles;
        this.orderPaneService = orderPaneService;

        dimension = new Dimension();
        dummyPane = new JTextPane();
    }

    @Override
    public void createOrderDocuments(Order order) {
        this.dimension = orderPaneService.getSize(dimension);

        LinkedList<OrderDocument> documents = new LinkedList<>();
        OrderDocument d = new OrderDocument();

        // Create Header and insert into first document
        try {
            d.insertString(0, order.getOrderType() + "\n" + order.getOrderTime() + "\n", kdsStyles.getHeadTextStyle());
        } catch (BadLocationException e) {
            log.error("Error inserting Header");
        }
        documents.add(d);

        for (Plate p : order.getPlates()) {
            if (checkDocumentLength(documents, d)){
                d = documents.getLast();
            }
            insertMain(p.getName(), d);

            for (String s : p.getSides()) {
                if (checkDocumentLength(documents, d)){
                    d = documents.getLast();
                }
                insertSide(s, d);
            }
        }
        order.setDocuments(documents);
        for (OrderDocument od : documents){
            od.setOrder(order);
        }
        log.info("Order added with " + documents.size() + " docs");

    }

    private void insertMain(String s, Document d) {
        try {
            d.insertString(d.getLength(), "\n " + s + " ", kdsStyles.getMainTextStyle());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void insertSide(String s, Document d) {
        try {
            d.insertString(d.getLength(), "\n      " + s, kdsStyles.getSideTextStyle());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    // Checks document length to see if a new page needs to be created
    private boolean checkDocumentLength(LinkedList<OrderDocument> documents, OrderDocument d) {
        if (getDocumentHeight(d) > (dimension.height - (kdsStyles.getFontSize() * 1.6))) {
            try {
                d.insertString(d.getLength(), "  --->", kdsStyles.getContTextStyle());
                d = new OrderDocument();
                d.insertString(0, "-- CONTINUED --", kdsStyles.getContTextStyle());
                documents.add(d);
                return true;
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * @param content the document to check
     * @return the height of the document as float
     */
    private float getDocumentHeight(Document content) {
        dummyPane.setSize(dimension);
        dummyPane.setDocument(content);

        return dummyPane.getPreferredSize().height;
    }
}
