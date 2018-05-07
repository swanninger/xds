package com.xds.ui;

import com.xds.ui.extensions.OrderPane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Scott Wanninger on 3/12/2018.
 */
@Slf4j
@Service
public class OrderPaneServiceImpl implements OrderPaneService {
    private List<OrderPane> panes;

    public OrderPaneServiceImpl(TimerService timerService){
        panes = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 10; i++) {
            OrderPane p = new OrderPane();
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
        for (OrderPane orderPane : panes) {
            orderPane.updateTimer();
        }
    }
}