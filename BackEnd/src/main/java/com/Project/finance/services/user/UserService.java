package com.Project.finance.services.user;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.Project.finance.entity.User;

public interface UserService {
    OAuth2User loadUser(OAuth2UserRequest userRequest);
}
