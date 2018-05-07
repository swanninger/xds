package com.xds.services;

import com.xds.ui.KdsUI;
import com.xds.ui.OrderPaneService;
import com.xds.uiComponents.OrderPane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

@Service
@Slf4j
public class KeyBindingServiceImpl implements KeyBindingService {

    private final KdsUI kdsUI;
    private final OrderService orderService;
    private final OrderPaneService orderPaneService;

    public KeyBindingServiceImpl(KdsUI kdsUI, OrderService orderService, OrderPaneService orderPaneService) {
        this.kdsUI = kdsUI;
        this.orderService = orderService;
        this.orderPaneService = orderPaneService;
        initBindings();
    }

    private void initBindings(){
        /* Bind 1-0 key to bump */
        for (int i = 0; i < 10; i++) {
            if (i != 0) bindBumpKey(i - 1, i);
            else bindBumpKey(9, 0);
        }
        bindPageHome("H");

        List<OrderPane> panes = orderPaneService.getPanes();
        for (int i = 0; i < 10; i++) {
            OrderPane p = panes.get(i);
            bindRecall("BACK_SPACE", p);
            bindPageLeft("LEFT", p);
            bindPageRight("RIGHT", p);
            p.addMouseListener(new BumpMouseAdapter(i));
        }
    }

    private void bindPageHome(String key) {
        kdsUI.getMainPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), "PageHome");
        kdsUI.getMainPanel().getActionMap().put("PageHome", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderService.pageHome();
            }
        });
    }

    private void bindPageRight(String key, JComponent component) {
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("" + key), "PageRight");
        component.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("" + key), "PageRight");
        component.getActionMap().put("PageRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderService.pageRight();
            }
        });
    }

    private void bindPageLeft(String key, JComponent component) {
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), "PageLeft");
        component.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(key), "PageLeft");
        component.getActionMap().put("PageLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderService.pageLeft();
            }
        });
    }

    private void bindRecall(String key, JComponent component) {
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), "Recall");
        component.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(key), "Recall");
        component.getActionMap().put("Recall", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderService.recallOrder();
            }
        });
    }

    private void bindExit(String key) {
//        kdsUI.getMainPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), "Exit");
//        kdsUI.getMainPanel().getActionMap().put("Exit", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);
//            }
//        });
    }

    public void bindBumpKey(int pane, int key){
        kdsUI.getMainPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("" + key), "Order" + pane);
        kdsUI.getMainPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("NUMPAD" + key), "Order" + pane);
        kdsUI.getMainPanel().getActionMap().put("Order" + pane, new BumpAction(pane));
    }

    private class BumpAction extends AbstractAction {
        int i;
        BumpAction(int i) {
           this.i = i;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            orderService.bumpOrder(i);
        }
    }

    public class BumpMouseAdapter extends MouseAdapter {
        private final int textPane;

        BumpMouseAdapter(int i){
            this.textPane = i;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("Touch " + textPane);
            orderService.bumpOrder(textPane);
        }
    }
}
