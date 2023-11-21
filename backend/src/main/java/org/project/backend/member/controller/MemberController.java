package org.project.backend.member.controller;

import java.sql.SQLException;

import javax.validation.Valid;

import org.project.backend.member.dto.OauthLoginRequest;
import org.project.backend.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/oauth/login")
    public ResponseEntity oauthLogin(@RequestBody @Valid OauthLoginRequest oauthLoginRequest) {
        try {
            log.info("OAuth 요청: {}", oauthLoginRequest);
            return ResponseEntity.ok().body(memberService.oauthLogin(oauthLoginRequest));
        } catch (SQLException e) {
            log.warn(
                    "Oauth 로그인 중 에러 발생: 소셜 타입 = {}, 요청 토큰 = {}",
                    oauthLoginRequest.getOauthLoginType(),
                    oauthLoginRequest.getCode()
            );
        }
        return ResponseEntity.badRequest().build();
    }
}
