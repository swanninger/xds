package com.xds.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.test.OAuth2ContextConfiguration;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableOAuth2Client
@ConfigurationProperties(prefix = "security.oauth2.client")
public class OauthConfiguration {

    private String client_id;

    @Bean
    public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext, ResourceOwnerPasswordResourceDetails details) {

        return new OAuth2RestTemplate(details, oauth2ClientContext);
    }

//    @Bean
//    protected OAuth2ProtectedResourceDetails resource() {
//
//        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
//
//        List scopes = new ArrayList<String>(2);
//        scopes.add("write");
//        scopes.add("read");
//        resource.setAccessTokenUri("localhost:8080/oauth/token");
//        resource.setClientId("client_id");
//        resource.setClientSecret("secret");
//        resource.setGrantType("password");
//        resource.setScope(scopes);
//
//        resource.setUsername("**USERNAME**");
//        resource.setPassword("**PASSWORD**");
//
//        return resource;
//    }
}
