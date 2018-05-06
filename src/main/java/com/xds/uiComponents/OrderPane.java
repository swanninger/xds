package com.xds.uiComponents;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.text.Document;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class OrderPane extends JTextPane {
    @Getter
    private OrderDocument orderDocument;
    @Getter @Setter
    private JLabel timerLabel;
    @Getter @Setter
    private boolean empty = true;
    private LocalDateTime orderTime;

    public void clearPane(Document d){
        setEmpty(true);
        timerLabel.setText("");
        setDocument(d);
    }

    public void setOrderDocument(OrderDocument orderDocument) {
        this.orderDocument = orderDocument;
        this.setDocument(orderDocument);
        this.orderTime = orderDocument.getOrder().getOrderTime();
        setEmpty(false);
        timerLabel.setText(orderTime.toLocalTime().toString());
    }

    public void updateTimer() {
        if (!isEmpty()){
//            orderTime = orderDocument.getOrder().getOrderTime().toLocalTime();
//
//            timerLabel.setText((LocalTime.now().minus(orderDocument.getOrder().getOrderTime().toLocalTime()).toString());
        }
    }
}