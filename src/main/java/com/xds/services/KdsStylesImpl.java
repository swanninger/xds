package com.xds.services;

import com.xds.config.SwingProperties;
import lombok.Getter;
import org.springframework.stereotype.Service;
import javax.swing.text.SimpleAttributeSet;

@Service
public class KdsStylesImpl implements KdsStyles {
    private final SwingProperties properties;

    @Getter
    private SimpleAttributeSet headTextStyle;
    @Getter
    private SimpleAttributeSet mainTextStyle;
    @Getter
    private SimpleAttributeSet sideTextStyle;
    @Getter
    private SimpleAttributeSet contTextStyle;

    public KdsStylesImpl(SwingProperties properties) {
        this.properties = properties;
    }

    private void initStyles(){

    }



}
