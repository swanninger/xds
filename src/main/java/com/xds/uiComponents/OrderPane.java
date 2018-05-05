package com.xds.uiComponents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import javax.swing.*;
import javax.swing.text.Document;

public class OrderPane extends JTextPane {
    @Getter
    private OrderDocument orderDocument;
    @Getter @Setter
    private JLabel timerLabel;
    @Getter @Setter
    private boolean empty = true;

    public void clearPane(Document d){
        setEmpty(true);
        timerLabel.setText("");
        setDocument(d);
    }

    public void setOrderDocument(OrderDocument orderDocument) {
        this.orderDocument = orderDocument;
        this.setDocument(orderDocument);
        setEmpty(false);
        timerLabel.setText(orderDocument.getOrder().getOrderTime().toString());
    }

    @Scheduled(fixedRate = 1000)
    @Async
    public void updateTimers() {
        if (!isEmpty()){
            timerLabel.setText(orderDocument.getOrder().getOrderTime().toString());
        }
    }
}