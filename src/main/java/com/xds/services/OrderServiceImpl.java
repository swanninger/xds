package com.xds.services;

import com.xds.UI.DocumentService;
import com.xds.UI.TextPaneService;
import com.xds.UI.TimerService;
import com.xds.domain.Order;
import com.xds.domain.OrderDocument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final DocumentService documentService;
    private final TimerService timerService;
    private final TextPaneService textPaneService;

    private List<OrderDocument> orderList;
    private Deque<OrderDocument> recallList;

    private Integer currentPage;
    private Integer numDocuments;

    public OrderServiceImpl(DocumentService documentService, TimerService timerService, TextPaneService textPaneService) {
        this.documentService = documentService;
        this.timerService = timerService;
        this.textPaneService = textPaneService;

        this.orderList = new CopyOnWriteArrayList<>();
        this.recallList = new LinkedList<>();

        currentPage = 0;
        numDocuments = 0; //Tracks total number of documents for paging calculations
    }

    @Override
    public void addOrder(Order order) {
        OrderDocument od = documentService.createOrderDocument(order);
        numDocuments += od.getDocuments().size();
        orderList.add(od);
        saveOrder(order);
    }

    @Override
    public void recallOrder() {
        log.info("recall order");
        orderList.add(0, recallList.pop());
    }

    /**
     *
     * @param i Text pane to bump
     */
    @Override
    public void bumpOrder(int i) {
        // TODO: 4/24/2018
        OrderDocument od = orderList.get(i);
        Order o = od.getOrder();




        log.info("Bump " + i);
        o.setBumpTime(LocalDateTime.now());
        saveOrder(o);
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
