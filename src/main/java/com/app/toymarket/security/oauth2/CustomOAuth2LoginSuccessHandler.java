package com.app.toymarket.security.oauth2;

import com.app.toymarket.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomOAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;
    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    public CustomOAuth2LoginSuccessHandler(UserService userService, OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        this.userService = userService;
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
        // TODO: Работа с токенами
        OAuth2AuthorizedClient user = oAuth2AuthorizedClientService.loadAuthorizedClient("google", oauthUser.getName());

        userService.processOAuthPostLogin(oauthUser.getEmail(), oauthUser.getName());

        response.sendRedirect("/");
    }
}
