package com.xds.UI;

import com.xds.domain.Order;
import com.xds.domain.OrderDocument;
import com.xds.domain.Plate;
import com.xds.services.KdsStyles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
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
    private final TextPaneService textPaneService;

    private Dimension dimension;
    private JTextPane dummyPane;

    public DocumentServiceImpl(KdsStyles kdsStyles, TextPaneService textPaneService) {
        this.kdsStyles = kdsStyles;
        this.textPaneService = textPaneService;
    }

    @Override
    public OrderDocument createOrderDocument(Order order) {
        this.dimension = textPaneService.getSize(dimension);

        LinkedList<Document> documents = new LinkedList<>();
        Document d = new DefaultStyledDocument();

        // Create Header and insert into first document
        try {
            d.insertString(0, order.getOrderType() + "\n" + order.getOrderTime() + "\n", kdsStyles.getHeadTextStyle());
        } catch (BadLocationException e) {
            log.error("Error inserting Header");
        }
        documents.add(d);

        for (Plate p : order.getPlates()) {
            checkDocumentLength(documents, d);
            insertMain(p.getName(), d);

            for (String s : p.getSides()) {
                checkDocumentLength(documents, d);
                insertSide(s, d);
            }
        }
        return new OrderDocument(documents, order);
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
    private void checkDocumentLength(LinkedList<Document> documents, Document d) {
        if (getDocumentHeight(d) > (dimension.height - (kdsStyles.getFontSize() * 1.6))) {
            try {
                d.insertString(d.getLength(), "  --->", kdsStyles.getContTextStyle());
                d = new DefaultStyledDocument();
                d.insertString(0, " CONTINUED From ", kdsStyles.getContTextStyle());
                documents.add(new DefaultStyledDocument());
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param content the document to check
     * @return the height of the document as int
     */
    private float getDocumentHeight(Document content) {
        if (dummyPane == null) {
            dummyPane = new JTextPane();
            dummyPane.setSize(dimension);
        }
        dummyPane.setDocument(content);

        return dummyPane.getPreferredSize().height;
    }
}
