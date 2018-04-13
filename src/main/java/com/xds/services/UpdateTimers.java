package com.xds.services;

import com.xds.UI.TimerJLabels;
import org.springframework.stereotype.Service;

/**
 * Created by PhazedOut on 4/5/2018.
 */
@Service
public class UpdateTimers {
    private final TimerJLabels timers;

    public UpdateTimers(TimerJLabels timers) {
        this.timers = timers;
    }
}
