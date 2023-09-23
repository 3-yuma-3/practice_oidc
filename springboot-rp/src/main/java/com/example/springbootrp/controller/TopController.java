package com.example.springbootrp.controller;

import com.example.springbootrp.WebOidcUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

@Controller
@AllArgsConstructor
public class TopController {
    @GetMapping("/")
    public String index(Model model) {
        OidcUser loginUser = WebOidcUserService.getLoginUser();

        var attributesMap = new HashMap<String, String>();

        attributesMap.put("subject", loginUser.getUserInfo().getSubject());
        attributesMap.put("emailVerified", String.valueOf(loginUser.getUserInfo().getEmailVerified()));
        attributesMap.put("preferredUsername", loginUser.getUserInfo().getPreferredUsername());
        attributesMap.put("givenName", loginUser.getUserInfo().getGivenName());
        attributesMap.put("familyName", loginUser.getUserInfo().getFamilyName());

        model.addAllAttributes(attributesMap);

        return "index";
    }
}
