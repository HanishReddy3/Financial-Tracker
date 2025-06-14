package com.Project.finance.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Project.finance.entity.User;
import com.Project.finance.services.user.UserServiceImplementation;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthO2Controller {

    private final UserServiceImplementation userServiceImplementation;

    @GetMapping("/login/google")
    public ResponseEntity<String> registerUser(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/google");
        return ResponseEntity.ok("Redirecting...");
    }

    @GetMapping("/home")
    public ResponseEntity<?> loginSucess(OAuth2AuthenticationToken oAuth2AuthenticationToken) throws IOException {
        User user = userServiceImplementation.registerUser(oAuth2AuthenticationToken);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("http://localhost:5173/home")).build();
    }

}
