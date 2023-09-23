package com.example.springbootrp;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashSet;
import java.util.Set;

@EnableWebSecurity
@AllArgsConstructor
public class OAuth2ClientSecurityConfig {
    private final WebOidcUserService webOidcUserService;

    /**
     * @param http
     * @return
     * @throws Exception
     * @see https://docs.spring.io/spring-security/reference/servlet/oauth2/login/advanced.html#oauth2login-advanced-oidc-user-service
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
            authorizeHttpRequests(authorize ->
                authorize
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            )
            .oauth2Login(oauth2 ->
                oauth2
                    .userInfoEndpoint(userInfo ->
                        userInfo
                            .oidcUserService(this.webOidcUserService)
                    )
            );

        return http.build();
    }

    /**
     * access_tokenを使って他のresourceを取得したい場合に使うぽい
     *
     * @return
     * @see https://docs.spring.io/spring-security/reference/servlet/oauth2/login/advanced.html#oauth2login-advanced-map-authorities-oauth2userservice
     */
    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        final OidcUserService delegate = new OidcUserService();

        return (userRequest) -> {
            // Delegate to the default implementation for loading a user
            OidcUser oidcUser = delegate.loadUser(userRequest);

            OAuth2AccessToken accessToken = userRequest.getAccessToken();
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

            // TODO
            // 1) Fetch the authority information from the protected resource using accessToken
            // 2) Map the authority information to one or more GrantedAuthority's and add it to mappedAuthorities

            // 3) Create a copy of oidcUser but use the mappedAuthorities instead
            oidcUser = new DefaultOidcUser(mappedAuthorities, oidcUser.getIdToken(), oidcUser.getUserInfo());

            return oidcUser;
        };
    }
}
