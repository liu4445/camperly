package org.project.backend.member.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class OauthLoginResponse {

	@NotNull
	private final String accessToken;
}
