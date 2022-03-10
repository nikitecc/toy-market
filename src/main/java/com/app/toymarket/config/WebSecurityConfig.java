package com.app.toymarket.config;

import com.app.toymarket.security.oauth2.CustomOAuth2LoginSuccessHandler;
import com.app.toymarket.security.oauth2.CustomOAuth2UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService oauth2UserService;
    private final CustomOAuth2LoginSuccessHandler oauth2LoginSuccessHandler;
    private final Environment environment;

    public WebSecurityConfig(CustomOAuth2UserService oauth2UserService, CustomOAuth2LoginSuccessHandler oauth2LoginSuccessHandler, Environment environment) {
        this.oauth2UserService = oauth2UserService;
        this.oauth2LoginSuccessHandler = oauth2LoginSuccessHandler;
        this.environment = environment;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // если активен профиль разработки, добавляем консоль h2 в общедоступный
        if (Arrays.stream(environment.getActiveProfiles()).filter(s -> s.contains("dev")).findAny().orElse(null) != null) {
            http
                    .headers().frameOptions().disable()
                    .and()
                    .authorizeRequests().antMatchers("/h2-console/**").permitAll();
        }

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/oauth2/**", "/registration").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(oauth2UserService)
                .and()
                .successHandler(oauth2LoginSuccessHandler)
                .and()
                .logout().permitAll().logoutSuccessUrl("/");
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/css/**")
                .antMatchers("/img/**")
                .antMatchers("/js/**");
    }
}

