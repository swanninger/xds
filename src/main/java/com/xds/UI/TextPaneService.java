package com.xds.UI;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

/**
 * Created by PhazedOut on 3/17/2018.
 */
public interface TextPaneService {

    Iterator<JTextPane> getPanes();
    Dimension getSize(Dimension dimension);

}
