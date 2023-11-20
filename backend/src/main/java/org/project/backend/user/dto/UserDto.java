package org.project.backend.user.dto;

import org.project.backend.constant.OauthLoginType;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserDto {

	private Long userId;
	private String loginId;
	private OauthLoginType oauthLoginType;
	private String name;

	@Builder
	public UserDto(Long userId, String loginId, OauthLoginType oauthLoginType, String name) {
		this.userId = userId;
		this.loginId = loginId;
		this.oauthLoginType = oauthLoginType;
		this.name = name;
	}
}
