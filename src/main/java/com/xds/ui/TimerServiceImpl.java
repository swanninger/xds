package com.xds.ui;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by PhazedOut on 4/5/2018.
 */
@Service
public class TimerServiceImpl implements TimerService {
    private List<JLabel> timers;

    public TimerServiceImpl() {
        initTimers();
    }

    private void initTimers(){
        timers = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            timers.add(new JLabel());
        }
    }

    public Iterator<JLabel> getTimers(){
        return timers.iterator();
    }

    public JLabel getTimer(int i) {
        return timers.get(i);
    }

//    @Override
//    @Scheduled(fixedRate = 5000)
//    public void updateTimers() {
//
//    }
}
