package com.xds.ui;

import com.xds.config.SwingProperties;
import com.xds.ui.extensions.OrderPane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Scott Wanninger on 3/12/2018.
 */
@Slf4j
@Service
public class OrderPaneServiceImpl implements OrderPaneService {
    private List<OrderPane> panes;
    private final SwingProperties properties;

    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    public OrderPaneServiceImpl(TimerService timerService, SwingProperties properties){
        this.properties = properties;
        panes = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 10; i++) {
            OrderPane p = new OrderPane(properties);
            panes.add(p);
            p.setTimerLabel(timerService.getTimer(i));
        }
    }

    /**
     * @param dimension optional
     * @return size of the first panes
     */
    @Override
    public Dimension getSize(Dimension dimension){
       return panes.get(0).getSize(dimension);
    }
    /**
     * @param i index to return, starts @ 0
     * @return OrderPane @ index
     */
    @Override
    public OrderPane getPane(int i) {
        return panes.get(i);
    }

    /**
     * @return OrderPanes List
     */
    @Override
    public List<OrderPane> getPanes(){
        return panes;
    }

    @Override
    @Scheduled(fixedDelay = 1000)
    @Async
    public void updateTimers() {
        LocalDateTime currentTime = LocalDateTime.now();
        for (OrderPane orderPane : panes) {
            if (!orderPane.isEmpty()){
                setTimer(orderPane, currentTime);
            }
        }
    }

    private void setTimer(OrderPane orderPane, LocalDateTime currentTime){
        LocalDateTime orderTime = orderPane.getOrderTime();
        JLabel timer = orderPane.getTimerLabel();
        Duration d = Duration.between(orderTime, currentTime);

        Long minutes = d.toMinutes();

        if (minutes < 2){
            timer.setBackground(Color.GREEN);
            timer.setForeground(Color.BLACK);
        }else if(minutes < 5){
            timer.setBackground(Color.YELLOW);
            timer.setForeground(Color.BLACK);
        }else{
            timer.setBackground(Color.RED);
            timer.setForeground(Color.BLACK);
        }

        String time = String.format("%02d : %02d : %02d", d.toHours(), minutes % 60, d.getSeconds() % 60);

        orderPane.getTimerLabel().setText(time);

    }
}