package org.project.backend.user.dto;

import org.project.backend.constant.OauthLoginType;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserDto {

	private Long userId;
	private Long loginId;
	private OauthLoginType oauthLoginType;
	private String name;

	@Builder
	public UserDto(Long userId, Long loginId, OauthLoginType oauthLoginType, String name) {
		this.userId = userId;
		this.loginId = loginId;
		this.oauthLoginType = oauthLoginType;
		this.name = name;
	}
}
