package com.xds.UI;

import javax.swing.*;
import java.util.Iterator;

/**
 * Created by PhazedOut on 4/5/2018.
 */
public interface TimerService {

    Iterator<JLabel> getTimers();

    void updateTimers();
}