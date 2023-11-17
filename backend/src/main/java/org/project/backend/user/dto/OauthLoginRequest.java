package org.project.backend.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.project.backend.constant.OauthLoginType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@RequiredArgsConstructor
public class OauthLoginRequest {

    @NotNull
    private final OauthLoginType oauthLoginType;

    @NotBlank
    private final String oauthToken;
}
