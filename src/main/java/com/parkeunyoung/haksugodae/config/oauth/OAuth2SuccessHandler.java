package com.parkeunyoung.haksugodae.config.oauth;

import com.parkeunyoung.haksugodae.jwt.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${jwt.secretKey}")
    private String secretKey;
    @Value("${front.redirect-url}")
    private String REDIRECT_URL;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String name = (String) oAuth2User.getAttributes().get("name");
        String token = JwtTokenUtil.createToken(name, secretKey, 36000000);

        System.out.println("token : " + token);
        String getRedirectUrl = UriComponentsBuilder.fromHttpUrl(REDIRECT_URL)
                .queryParam("token", "Bearer " + token)
                .build().toUriString();
        getRedirectStrategy().sendRedirect(request, response, getRedirectUrl);
    }
}
