package com.xds.UI;

import com.xds.services.OrderService;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Scott Wanninger on 3/12/2018.
 */
@Service
public class TextPaneServiceImpl implements TextPaneService {
    private final OrderService orderService;
    private CopyOnWriteArrayList<JTextPane> panes;

    public TextPaneServiceImpl(OrderService orderService){
        this.orderService = orderService;

        panes = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 10; i++) {
            JTextPane p = new JTextPane();
            p.addMouseListener(new BumpMouseAdapter(orderService, i));
            panes.add(p);
        }
    }

    public Iterator<JTextPane> getPanes(){
        return panes.iterator();
    }

}
