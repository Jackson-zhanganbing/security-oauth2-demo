package com.changan.securitydemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zab
 * @date 2019/6/21 14:32
 */
@Configuration
@ConfigurationProperties("authentication.oauth")
public class TokenCheckInfoConfig {
    @Value("clientId")
    private String clientId;
    @Value("secret")
    private String secret;
    @Value("tokenValidityInSeconds")
    private String tokenValidityInSeconds;
    @Value("checkToken")
    private String checkToken;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getTokenValidityInSeconds() {
        return tokenValidityInSeconds;
    }

    public void setTokenValidityInSeconds(String tokenValidityInSeconds) {
        this.tokenValidityInSeconds = tokenValidityInSeconds;
    }

    public String getCheckToken() {
        return checkToken;
    }

    public void setCheckToken(String checkToken) {
        this.checkToken = checkToken;
    }
}
