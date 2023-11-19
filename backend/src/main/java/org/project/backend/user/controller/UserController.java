package org.project.backend.user.controller;

import java.sql.SQLException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.project.backend.user.dto.OauthLoginRequest;
import org.project.backend.user.dto.OauthLoginResponse;
import org.project.backend.user.service.UserService;
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

    private final UserService userService;

    @PostMapping("/oauth/login")
    public ResponseEntity oauthLogin(@RequestBody @Valid OauthLoginRequest oauthLoginRequest) throws SQLException {
        log.info("OAuth 요청: {}", oauthLoginRequest);
        return ResponseEntity.ok().body(userService.oauthLogin(oauthLoginRequest));
    }
}
