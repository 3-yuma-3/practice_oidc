package com.example.springbootrp;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebOidcUserService extends OidcUserService {
    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        var oidcUser = super.loadUser(userRequest);

        List<GrantedAuthority> authList =
            List.of(new OAuth2UserAuthority("hoge", oidcUser.getAttributes()));

        return new DefaultOidcUser(authList, userRequest.getIdToken(), oidcUser.getUserInfo());
    }

    public static OidcUser getLoginUser() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof OidcUser oidcUser) return oidcUser;

        throw new RuntimeException("認証情報の取得に失敗しました。");
    }
}
