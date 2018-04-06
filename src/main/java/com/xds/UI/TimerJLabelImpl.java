package com.xds.UI;

import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by PhazedOut on 4/5/2018.
 */
@Service
public class TimerJLabelImpl implements TimerJLabels {
    private CopyOnWriteArrayList<JLabel> timers;

    public TimerJLabelImpl() {
        timers = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            timers.add(new JLabel());
        }
    }

    public Iterator<JLabel> getTimers(){
        return timers.iterator();
    }
}
