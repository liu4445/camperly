package org.project.backend.member.dto;

import org.project.backend.constant.OauthLoginType;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberDto {

	private Long memberId;
	private String loginId;
	private OauthLoginType oauthLoginType;
	private String name;

	@Builder
	public MemberDto(String loginId, OauthLoginType oauthLoginType, String name) {
		this.loginId = loginId;
		this.oauthLoginType = oauthLoginType;
		this.name = name;
	}
}
