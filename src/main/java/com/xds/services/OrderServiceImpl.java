package com.xds.services;

import com.xds.ui.DocumentService;
import com.xds.ui.OrderPaneService;
import com.xds.config.SwingProperties;
import com.xds.domain.Order;
import com.xds.uiComponents.OrderDocument;
import com.xds.uiComponents.OrderPane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import java.time.LocalDateTime;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final DocumentService documentService;
    private final OrderPaneService orderPaneService;
    private final SwingProperties properties;

    private List<OrderDocument> orderList;
    private Deque<Order> recallList;

    private Integer currentPage;

    private Document dummyDocument = new DefaultStyledDocument();

    public OrderServiceImpl(DocumentService documentService, OrderPaneService orderPaneService, SwingProperties properties) {
        this.documentService = documentService;
        this.orderPaneService = orderPaneService;
        this.properties = properties;

        this.orderList = new CopyOnWriteArrayList<>();
        this.recallList = new LinkedList<>();

        currentPage = 0;
    }

    @Override
    public void addOrder(Order o) {
        documentService.createOrderDocuments(o);
        orderList.addAll(o.getDocuments());
        saveOrder(o);
        log.info("Order added");
        updateOrders();
    }

    @Override
    public void recallOrder() {
        log.info("recall order");
        Order o = recallList.pop();
        orderList.addAll(0, o.getDocuments());
        updateOrders();
    }

    /**
     * @param i Text pane to bump
     */
    @Override
    public void bumpOrder(int i) {
        OrderPane orderPane = orderPaneService.getPane(i);
        log.info("Bump " + i);
        if (!orderPane.isEmpty()){ //check to see if panel is empty
            OrderDocument oDocument = orderPane.getOrderDocument();
            Order order = oDocument.getOrder();

            this.orderList = // This should filter out all OrderDocuments with the same order
                    orderList.stream()
                    .filter(t -> t.getOrder() != order)
                    .collect(Collectors.toList());

            recallList.push(order);
            if (recallList.size() > properties.getMaxRecall()){
                recallList.poll();
            }
            log.info("Order Cleared");
            order.setBumpTime(LocalDateTime.now());
            saveOrder(order);
            updateOrders();
        }
    }

    @Override
    public Boolean saveOrder(Order order) {
        // TODO: 4/24/2018
        return null;
    }

    @Override
    public void updateOrders() {
        Iterator<OrderDocument> orders = orderList.iterator();

        for (OrderPane orderPane : orderPaneService.getPanes()) {
            if (orders.hasNext()){
                orderPane.setOrderDocument(orders.next());
            }
            else {
                orderPane.clearPane(dummyDocument);
            }
        }
        log.info("Orders Updated");
    }

    @Override
    public void pageRight() {
        log.info("Page Right");
//                System.out.println(orderList.size() / paneList.size());
//                if (numDocuments / paneList.size() > currentPage) {
//                    currentPage++;
//                    page.setText("Page " + (currentPage + 1) + " ");
//                    updateOrders();
//                } else {
//                    System.out.println("No more pages");
//                }
//                currentPage++;
//                page.setText("Page " + (currentPage + 1) + " ");
    }

    @Override
    public void pageLeft() {
        log.info("Page Left");
//                if (currentPage > 0) {
//                    currentPage--;
//                    page.setText("Page " + (currentPage + 1) + " ");
//                    updateOrders();
//                } else {
//                    System.out.println("At first page");
//                }
    }

    @Override
    public void pageHome() {
        log.info("Home");
//                if (currentPage != 0) {
//                    currentPage = 0;
//                    page.setText("Page 1");
//                    updateOrders();
//                } else {
//                    System.out.println("Already Home");
//                }
    }
}
