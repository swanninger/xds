package com.xds.services;

import com.xds.UI.TimerJLabels;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by PhazedOut on 4/5/2018.
 */
@Service
public class TimerServiceImpl implements TimerService{
    private final TimerJLabels timers;

    public TimerServiceImpl(TimerJLabels timers) {
        this.timers = timers;
    }

    @Scheduled(fixedRate = 5000)
    public void updateTimers() {
        //todo
    }
}
