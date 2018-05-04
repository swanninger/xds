package com.xds.services;

import com.xds.UI.DocumentService;
import com.xds.domain.Order;
import com.xds.domain.OrderDocument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final DocumentService documentService;

    private List<OrderDocument> orderList;
    private Deque<OrderDocument> recallList;

    private Integer currentPage;
    private Integer numDocuments;

    public OrderServiceImpl(DocumentService documentService) {
        this.documentService = documentService;
        this.orderList = new CopyOnWriteArrayList<>();
        this.recallList = new LinkedList<>();

        currentPage = 0;
        numDocuments = 0;
    }

    @Override
    public void addOrder(Order order) {
        orderList.add(documentService.createOrderDocument(order));
        saveOrder(order);
    }

    @Override
    public void recallOrder() {
        log.info("recall order");
        orderList.add(0, recallList.pop());
    }

    @Override
    public void bumpOrder(int textPane) {
        // TODO: 4/24/2018
        log.info("Bump " + textPane);
//        saveOrder();
    }

    @Override
    public Boolean saveOrder(Order order) {
        // TODO: 4/24/2018
        return null;
    }

    @Override
    public void updateOrders() {
        // TODO: 5/3/2018  
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
