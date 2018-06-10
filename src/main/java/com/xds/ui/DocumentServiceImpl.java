package com.xds.ui;

import com.xds.domain.Mod;
import com.xds.domain.Order;
import com.xds.ui.extensions.OrderDocument;
import com.xds.domain.Plate;
import com.xds.services.KdsStyles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

/**
 * 5/4/2018 piKDS
 */

@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {
    private final KdsStyles kdsStyles;
    private final OrderPaneService orderPaneService;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    private Dimension dimension;
    private JTextPane dummyPane;

    public DocumentServiceImpl(KdsStyles kdsStyles, OrderPaneService orderPaneService) {
        this.kdsStyles = kdsStyles;
        this.orderPaneService = orderPaneService;

        this.dummyPane = new JTextPane();
    }

    @Override
    public void createOrderDocuments(Order order) {
        if(this.dimension == null){
            this.dimension = orderPaneService.getSize();
        }


        LinkedList<OrderDocument> documents = new LinkedList<>();
        OrderDocument d = new OrderDocument();

        // Create Header and insert into first document
        try {
            d.insertString(0,
                    createHeader(order),
                    kdsStyles.getHeadTextStyle());
        } catch (BadLocationException e) {
            log.error("Error inserting Header");
        }
        documents.add(d);

        if (order.getPlates() != null) {
            for (Plate p : order.getPlates()) {
                if (checkDocumentLength(documents, d)) {
                    d = documents.getLast();
                }
                insertPlate(p, d);

                if (p.getMods() != null) {
                    for (Mod m : p.getMods()) {
                        if (checkDocumentLength(documents, d)) {
                            d = documents.getLast();
                        }
                        insertMod(m, d);
                    }
                }
            }
        }

        order.setDocuments(documents);
        for (OrderDocument od : documents) {
            od.setOrder(order);
        }
//        log.info("Order added with " + documents.size() + " docs");

    }

    private void insertPlate(Plate p, Document d) {
        try {
            d.insertString(d.getLength(), "\n " + p + " ", kdsStyles.getMainTextStyle());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void insertMod(Mod mod, Document d) {
        try {
            d.insertString(d.getLength(), "\n      " + mod, kdsStyles.getSideTextStyle());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private String createHeader(Order order){
        StringBuilder sb = new StringBuilder();

        sb.append(order.getOrderMode()).append("   ");

        if (order.getNameOnOrder() != null){
            String s = lengthCheck(order.getNameOnOrder());
            sb.append(s);
        }

        sb.append("\n")
                .append(order.getOrderTime().format(dtf))
                .append("\n");

        return sb.toString();
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

    private String lengthCheck(String s){

        if(s.length() > kdsStyles.getMaxStringSize()){
            s = s.substring(0, kdsStyles.getMaxStringSize());
        }
        return s;
    }
}
