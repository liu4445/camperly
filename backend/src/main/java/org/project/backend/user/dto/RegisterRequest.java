package org.project.backend.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
@RequiredArgsConstructor
public class RegisterRequest {

    @NotBlank
    private final String id;

    @NotBlank
    private final String password;

    @NotBlank
    private final String name;

    @NotBlank
    private final String tel;
}
