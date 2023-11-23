package org.project.backend.member.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.project.backend.constant.OauthLoginType;
import org.project.backend.member.dto.OauthLoginRequest;
import org.project.backend.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
    @RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{oauthLoginType}/login")
    public ResponseEntity oauthLogin(
            @PathVariable("oauthLoginType") OauthLoginType oauthLoginType,
            @RequestParam String code
    ) {
        try {
            log.info("OAuth 요청: 소셜 타입 = {}, 요청 토큰 = {}", oauthLoginType, code);
            return ResponseEntity.ok().body(memberService.oauthLogin(oauthLoginType, code));
        } catch (SQLException e) {
            log.warn(
                    "Oauth 로그인 중 에러 발생: 소셜 타입 = {}, 요청 토큰 = {}",
                    oauthLoginType,
                    code
            );
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/auth")
    public ResponseEntity auth(HttpServletRequest httpServletRequest) {
        try {
            memberService.validateToken(httpServletRequest);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
