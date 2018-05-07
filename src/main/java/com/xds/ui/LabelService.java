package com.xds.ui;

import javax.swing.*;
import java.time.Duration;

public interface LabelService {
    void setDisplayName(String s);

    void setPageText(String s);

    void setAlert(String s, Duration duration);

    JLabel getAlert();
    JLabel getDisplayName();
    JLabel getPageNumber();
}
