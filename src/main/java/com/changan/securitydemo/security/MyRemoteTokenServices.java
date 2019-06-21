package com.changan.securitydemo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

public class MyRemoteTokenServices extends RemoteTokenServices {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
        logger.info("=====验证token开始：" + accessToken);
        OAuth2Authentication authentication = super.loadAuthentication(accessToken);
        logger.info("=====验证token结束：" + accessToken);
        return authentication;
    }

    @Override
    public OAuth2AccessToken readAccessToken(String accessToken) {
        // TODO Auto-generated method stub
        return super.readAccessToken(accessToken);
    }

}
