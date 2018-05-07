package com.xds.ui;

import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.time.Duration;

@Getter
@Service
public class LabelServiceImpl implements LabelService {

    private JLabel displayName = new JLabel();
    private JLabel alert = new JLabel();
    private JLabel pageNumber = new JLabel();

    @Override
    public void setDisplayName(String s) {
        this.displayName.setText(s);
    }

    @Override
    public void setPageText(String s) {
        this.pageNumber.setText(s);
    }

    @Override
    public void setAlert(String s, Duration duration) {
        this.alert.setText(s);
    }
}
