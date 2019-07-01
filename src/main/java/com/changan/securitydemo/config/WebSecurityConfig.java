package com.changan.securitydemo.config;

import com.changan.securitydemo.config.hanlder.MyAuthenticationFailureHandler;
import com.changan.securitydemo.config.hanlder.MyAuthenticationSuccessHandler;
import com.changan.securitydemo.security.MySecurityInterceptor;
import com.changan.securitydemo.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author zhanganbing
 * @date 2018/11/25/025 22:18
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/swagger-resources/**",
            "/v2/api-docs",
            "/webjars/**",
            // static resources
            "/**.js",
            "/**/**.js",
            "/**.css",
            "/**/**.css",
            "/static/**",
            // login
            "/**/token/checkToken",
            // inner api
            "/inner_api/**",
            "/",
            // 为了方便查看swagger接口文档效果，设置成白名单，生产环境必须删除此项配置
            "/swagger-ui.html"
    };

    @Autowired
    private MySecurityInterceptor mySecurityInterceptor;

    @Autowired
    private MyAuthenticationSuccessHandler successHandler;

    @Autowired
    private MyAuthenticationFailureHandler failureHandler;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //user Details Service验证
        auth.userDetailsService(userDetailsServiceImpl);
       /* auth.inMemoryAuthentication()
                .withUser("zab")
                .password("zab")
                .authorities("ROLE_ADMIN");*/
    }

    /**
     * 配置访问白名单、指定用户名密码、指定认证失败（成功）处理器、配置拦截器
     *
     * @param http
     * @return void
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //允许访问静态资源
                .antMatchers(AUTH_WHITELIST).permitAll()
                //任何请求,登录后可以访问
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                //登录页面用户任意访问
                .loginPage("/login").permitAll()
                .and()
                //注销行为任意访问
                .logout().permitAll();
        http.addFilterBefore(mySecurityInterceptor, FilterSecurityInterceptor.class);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**");
    }
}
