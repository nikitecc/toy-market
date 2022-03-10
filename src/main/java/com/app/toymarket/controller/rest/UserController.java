package com.app.toymarket.controller.rest;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @GetMapping("/user")
    public Principal getPrincipalUser(Principal user) {
        return user;
    }

    @GetMapping("/token-refresh")
    public OAuth2RefreshToken tokenRefresh(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient user) {
        return user.getRefreshToken();
    }

    @GetMapping("/token-access")
    public OAuth2AccessToken tokenAccess(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient user) {
        return user.getAccessToken();
    }
}