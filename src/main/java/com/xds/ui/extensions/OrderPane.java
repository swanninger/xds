package com.xds.ui.extensions;

import com.xds.config.SwingProperties;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.time.LocalDateTime;


public class OrderPane extends JTextPane {
    private final SwingProperties properties;

    @Getter
    private OrderDocument orderDocument;
    @Getter @Setter
    private JLabel timerLabel;
    @Getter @Setter
    private boolean empty = true;
    @Getter
    private LocalDateTime orderTime;

    public OrderPane(SwingProperties properties) {
        this.properties = properties;
    }

    public void clearPane(Document d){
        setEmpty(true);
        timerLabel.setText("");
        setDocument(d);
        orderTime = null;
        timerLabel.setBackground(Color.GRAY);
    }

    public void setOrderDocument(OrderDocument orderDocument) {
        this.orderDocument = orderDocument;
        this.setDocument(orderDocument);
        this.orderTime = orderDocument.getOrder().getOrderTime();
        setEmpty(false);
    }
}