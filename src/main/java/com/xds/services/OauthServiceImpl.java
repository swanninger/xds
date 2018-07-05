package com.xds.services;

import com.xds.config.OauthConfiguration;
import com.xds.ui.LabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class OauthServiceImpl implements OauthService, ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {
    private ApplicationContext context;
    private OAuth2RestOperations oAuth2RestTemplate;
    private final OauthConfiguration oauthConfiguration;
    private final LabelService labelService;


    public OauthServiceImpl(OAuth2RestOperations oAuth2RestTemplate, OauthConfiguration oauthConfiguration, LabelService labelService) {
        this.oAuth2RestTemplate = oAuth2RestTemplate;
        this.oauthConfiguration = oauthConfiguration;
        this.labelService = labelService;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        oauthLogin();
    }

    private void oauthLogin() {
        try {
            oAuth2RestTemplate.getAccessToken();
            labelService.setAlert(oAuth2RestTemplate.getForObject(oauthConfiguration.getBaseUrl(), String.class), Duration.of(5, ChronoUnit.SECONDS));
        } catch (Exception e) {
            labelService.setAlert("Invalid login...", Duration.of(5, ChronoUnit.SECONDS));
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.exit(0);
        }
    }
}
