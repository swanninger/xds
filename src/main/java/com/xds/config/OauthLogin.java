package com.xds.config;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

@Service
public class OauthLogin {
    private OAuth2RestTemplate oAuth2RestTemplate;

    public OauthLogin(OAuth2RestTemplate oAuth2RestTemplate) {
        this.oAuth2RestTemplate = oAuth2RestTemplate;

        oAuth2RestTemplate.getAccessToken();
    }


}
