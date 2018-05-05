package com.xds.uiComponents;


import com.xds.domain.Order;
import lombok.Getter;
import lombok.Setter;
import javax.swing.text.DefaultStyledDocument;

public class OrderDocument extends DefaultStyledDocument {
    @Getter @Setter
    private Order order;
}
