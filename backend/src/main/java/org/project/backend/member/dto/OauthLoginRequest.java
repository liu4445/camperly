package org.project.backend.member.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.project.backend.constant.OauthLoginType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class OauthLoginRequest {

    @NotNull
    private final OauthLoginType oauthLoginType;

    @NotBlank
    private final String code;
}
