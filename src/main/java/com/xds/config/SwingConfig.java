package com.xds.config;

import com.xds.UI.KTextPanes;
import com.xds.UI.KdsUI;
import com.xds.UI.TimerJLabels;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by PhazedOut on 2/26/2018.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class SwingConfig {

    @Value("${ui.displayName}")
    String displayName;

    public static PropertySourcesPlaceholderConfigurer properties(){
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        return propertySourcesPlaceholderConfigurer;
    }

    @Bean
    public KdsUI createKdsUI(KTextPanes KTextPanes, TimerJLabels timerJLabels){
        KdsUI kdsUI = new KdsUI(KTextPanes, timerJLabels);
        kdsUI.setDisplayLabel(displayName);
        return kdsUI;
    }
}
