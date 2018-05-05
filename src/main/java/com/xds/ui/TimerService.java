package com.xds.ui;

import javax.swing.*;
import java.util.Iterator;

/**
 * Created by PhazedOut on 4/5/2018.
 */
public interface TimerService {

    Iterator<JLabel> getTimers();
    JLabel getTimer(int i);
//    void updateTimers();

}
