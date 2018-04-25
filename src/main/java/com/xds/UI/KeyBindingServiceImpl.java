package com.xds.UI;

import com.xds.services.OrderService;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Service
public class KeyBindingServiceImpl implements KeyBindingService{

    private final KdsUI kdsUI;
    private final OrderService orderService;

    public KeyBindingServiceImpl(KdsUI kdsUI, OrderService orderService) {
        this.kdsUI = kdsUI;
        this.orderService = orderService;
    }

    private void initBindings(){


    }

    // TODO: 4/24/2018 implement
    private void bindPageHome(String key) {
        kdsUI.getMainPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), "PageHome");
        kdsUI.getMainPanel().getActionMap().put("PageHome", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if (currentPage != 0) {
//                    currentPage = 0;
//                    page.setText("Page 1");
//                    updateOrders();
//                } else {
//                    System.out.println("Already Home");
//                }
            }
        });
    }








    public void bindBumpKey(int pane, String key){
        kdsUI.getMainPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), "Order" + pane);
        kdsUI.getMainPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("NUMPAD" + key), "Order" + pane);
        kdsUI.getMainPanel().getActionMap().put("Order" + pane, new BumpAction(pane));
    }

    private class BumpAction extends AbstractAction {
        int pane;
        public BumpAction(int pane) {
           this.pane = pane;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            orderService.bumpOrder(pane);
        }
    }


}
