package com.changan.securitydemo.config;

import com.changan.securitydemo.security.MyRemoteTokenServices;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Configuration
public class OAuthTokenConfig {

    @Primary
    @Bean
    public RemoteTokenServices tokenService() {
        RemoteTokenServices tokenService = new MyRemoteTokenServices();
        DefaultAccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();
        RestTemplate restTemplate = new RestTemplate();

        Collection<HttpMessageConverter<?>> converts = Collections2.filter(restTemplate.getMessageConverters(), new Predicate<HttpMessageConverter<?>>() {
            @Override
            public boolean apply(HttpMessageConverter<?> httpMessageConverter) {
                return !(httpMessageConverter instanceof MappingJackson2XmlHttpMessageConverter);
            }
        });

        restTemplate.setMessageConverters(Lists.newArrayList(converts));
        tokenService.setRestTemplate(restTemplate);
        tokenService.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
        tokenService.setClientId("zab");
        tokenService.setClientSecret("zab");
        tokenService.setAccessTokenConverter(tokenConverter);
        return tokenService;
    }
}
