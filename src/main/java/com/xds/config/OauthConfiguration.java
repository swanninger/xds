package com.xds.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.context.request.RequestContextListener;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "xds.server")
@Getter
@Setter
public class OauthConfiguration {
    private String baseUrl;
    private String accessTokenUri;
    private String clientId;
    private String clientSecret;
    private String grantType;
    private String username;
    private String password;
    private String mod;

    @Bean
    protected ResourceOwnerPasswordResourceDetails resource() {

        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();

        List scopes = new ArrayList<String>(2);
        scopes.add("write");
        scopes.add("read");

        resource.setId("oauth2");
        resource.setAccessTokenUri(baseUrl + accessTokenUri);
        resource.setClientId(clientId);
        resource.setClientSecret(clientSecret);
        resource.setGrantType(grantType);
//        resource.setScope(scopes);

        resource.setUsername(username);
        resource.setPassword(password);

        return resource;
    }

    @Bean
    public OAuth2RestOperations oAuth2RestOperations() {
        return new OAuth2RestTemplate(resource());

    }


}
