package com.xds.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.awt.*;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "ui")
public class SwingProperties {

    private String displayLabelText;
    private int maxRecall;


//    Styles
    private int fontSize;

    private String newTime;
    private String oldTime;
    private String lateTime;

    private String mainColor;
}
