package com.xds.services;

import com.xds.api.v1.mapper.OrderMapper;
import com.xds.api.v1.model.OrderDTO;
import com.xds.repositories.OrderRepository;
import com.xds.ui.DocumentService;
import com.xds.ui.LabelService;
import com.xds.ui.OrderPaneService;
import com.xds.config.SwingProperties;
import com.xds.domain.Order;
import com.xds.ui.extensions.OrderDocument;
import com.xds.ui.extensions.OrderPane;
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
    private final LabelService labelService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private List<OrderDocument> orderList;
    private Deque<Order> recallList;

    private Integer currentPage;

    private Document dummyDocument = new DefaultStyledDocument();

    public OrderServiceImpl(DocumentService documentService, OrderPaneService orderPaneService,
                            SwingProperties properties, LabelService labelService, OrderRepository orderRepository,
                            OrderMapper orderMapper) {
        this.documentService = documentService;
        this.orderPaneService = orderPaneService;
        this.properties = properties;
        this.labelService = labelService;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;

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
    public void addOrder(OrderDTO orderDTO) {
        addOrder(orderMapper.orderDtoToOrder(orderDTO));
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
        if (!orderPane.isEmpty()) { //check to see if panel is empty
            OrderDocument oDocument = orderPane.getOrderDocument();
            Order order = oDocument.getOrder();

            this.orderList = // This should filter out all OrderDocuments with the same order
                    orderList.stream()
                            .filter(t -> t.getOrder() != order)
                            .collect(Collectors.toList());

            recallList.push(order);
            if (recallList.size() > properties.getMaxRecall()) {
                recallList.poll();
            }
            log.info("Order Cleared");
            order.setBumpTime(LocalDateTime.now());
            saveOrder(order);
            if (currentPage > orderList.size() / 10) {
                pageLast();
            } else updateOrders();
        }
    }

    @Override
    public Boolean saveOrder(Order order) {
        orderRepository.save(order);
        return null;
    }

    @Override
    public void updateOrders() {
        Iterator<OrderDocument> orders = orderList.listIterator(currentPage * 10);

        for (OrderPane orderPane : orderPaneService.getPanes()) {
            if (orders.hasNext()) {
                orderPane.setOrderDocument(orders.next());
            } else {
                orderPane.clearPane(dummyDocument);
            }
        }
        log.info("Orders Updated");
    }

    @Override
    public void pageRight() {
        log.info("Page Right");

        if (orderList.size() / 10 > currentPage) {
            currentPage++;
            labelService.setPageText("Page " + (currentPage + 1) + " ");
            updateOrders();
        } else {
            log.info("No more pages");
        }
    }

    @Override
    public void pageLeft() {
        log.info("Page Left");
        if (currentPage > 0) {
            currentPage--;
            labelService.setPageText("Page " + (currentPage + 1) + " ");
            updateOrders();
        } else {
            log.info("At first page");
        }
    }

    @Override
    public void pageHome() {
        log.info("Home");
        if (currentPage != 0) {
            currentPage = 0;
            labelService.setPageText("Page 1");
            updateOrders();
        } else {
            log.info("Already Home");
        }
    }

    @Override
    public void pageLast() {
        currentPage = orderList.size() / 10;
        updateOrders();
    }
}
