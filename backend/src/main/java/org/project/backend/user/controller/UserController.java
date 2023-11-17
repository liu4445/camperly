package org.project.backend.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.backend.user.dto.LoginRequest;
import org.project.backend.user.dto.OauthLoginRequest;
import org.project.backend.user.dto.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest) {
        log.info("login 요청: {}", loginRequest);

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity register(@RequestBody @Valid RegisterRequest registerRequest) {
        log.info("회원가입 요청: {}", registerRequest);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/oauth/login")
    public ResponseEntity oauthLogin(@RequestBody @Valid OauthLoginRequest oauthLoginRequest) {
        log.info("OAuth 요청: {}", oauthLoginRequest);

        return ResponseEntity.ok().build();
    }
}
